package com.insurance.policy.service;

import com.insurance.policy.model.Policy;
import com.insurance.policy.model.PolicyObject;
import com.insurance.policy.model.SubObject;
import com.insurance.policy.risk.Risk;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PremiumCalculatorTest {
    @Test
    public void should_Return_0_When_No_Object_In_Policy() {
        //given
        var calculator = new PremiumCalculator();
        var policy = new Policy("", Policy.Status.APPROVED, List.of());
        //when
        var cost = calculator.calculate(policy);
        //then
        assertEquals(0, cost.policyCost());
    }

    @Test
    public void should_Return_0_When_No_SubObject_In_Policy() {
        //given
        var calculator = new PremiumCalculator();
        var policy = new Policy("", Policy.Status.APPROVED, List.of(new PolicyObject("", List.of())));
        //when
        var cost = calculator.calculate(policy);
        //then
        assertEquals(0, cost.policyCost());
    }

    @Test
    public void should_Compute_Cost_When_Under_Fire_Threshold_Value() {
        //given
        var calculator = new PremiumCalculator();
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 10.0, Risk.Type.FIRE)))));
        //when
        var cost = calculator.calculate(policy);
        //then
        assertEquals(0.14, cost.policyCost());
    }

    @Test
    public void should_Compute_Cost_When_Over_Fire_Threshold_Value() {
        //given
        var calculator = new PremiumCalculator();
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 1000.0, Risk.Type.FIRE)))));
        //when
        var cost = calculator.calculate(policy);
        //then
        assertEquals(24, cost.policyCost());
    }

    @Test
    public void should_Compute_Cost_When_Under_Theft_Threshold_Value() {
        //given
        var calculator = new PremiumCalculator();
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 10.0, Risk.Type.THEFT)))));
        //when
        var cost = calculator.calculate(policy);
        //then
        assertEquals(1.1, cost.policyCost());
    }

    @Test
    public void should_Compute_Cost_When_Over_Theft_Threshold_Value() {
        //given
        var calculator = new PremiumCalculator();
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 1000.0, Risk.Type.THEFT)))));
        //when
        var cost = calculator.calculate(policy);
        //then
        assertEquals(50, cost.policyCost());
    }

    @Test
    public void should_Compute_Cost_When_Both_Risk() {
        //given
        var calculator = new PremiumCalculator();
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 10.0, Risk.Type.FIRE),
                                new SubObject("", 10.0, Risk.Type.THEFT),
                                new SubObject("", 10.0, Risk.Type.FIRE)))));
        //when
        var cost = calculator.calculate(policy);
        //then
        assertEquals("1.38", String.format("%.2f", cost.policyCost()));
    }
}
