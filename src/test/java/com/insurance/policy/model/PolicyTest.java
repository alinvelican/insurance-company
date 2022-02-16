package com.insurance.policy.model;


import static org.junit.jupiter.api.Assertions.*;

import com.insurance.policy.risk.Risk;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PolicyTest {
    @Test
    void should_Return_No_Group_And_No_SubObject_When_Empty_List_Objects() {
        //given
        var policy = new Policy("", Policy.Status.APPROVED, List.of());

        //when
        var groupSubObjectsByRisk = policy.groupSubObjectsByRisk();
        var subObjects = policy.getSubObjects();

        //then
        assertAll(
                () -> assertEquals(0, groupSubObjectsByRisk.size()),
                () -> assertEquals(0, subObjects.size())
        );
    }

    @Test
    void should_Return_No_Group_And_No_SubObject_When_Empty_List_SubObjects() {
        //given
        var policy = new Policy("", Policy.Status.APPROVED, List.of(new PolicyObject("", List.of())));

        //when
        var groupSubObjectsByRisk = policy.groupSubObjectsByRisk();
        var subObjects = policy.getSubObjects();

        //then
        assertAll(
                () -> assertEquals(0, groupSubObjectsByRisk.size()),
                () -> assertEquals(0, subObjects.size())
        );
    }

    @Test
    void should_Return_1_SubObject() {
        //given
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 0.0, Risk.Type.FIRE)))));
        //when
        var subObjects = policy.getSubObjects();

        //then
        assertEquals(1, subObjects.size());
    }

    @Test
    void should_Return_1_Group() {
        //given
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 0.0, Risk.Type.FIRE)))));
        //when
        var groupSubObjectsByRisk = policy.groupSubObjectsByRisk();

        //then
        assertAll(
                () -> assertEquals(1, groupSubObjectsByRisk.size()),
                () -> assertEquals(1, groupSubObjectsByRisk.get(Risk.of(Risk.Type.FIRE)).size())
        );
    }

    @Test
    void should_Return_Multiple_Groups() {
        //given
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 0.0, Risk.Type.FIRE),
                                new SubObject("tv", 0.0, Risk.Type.THEFT),
                                new SubObject("", 0.0, Risk.Type.FIRE)))));
        //when
        var groupSubObjectsByRisk = policy.groupSubObjectsByRisk();

        //then
        assertAll(
                () -> assertEquals(2, groupSubObjectsByRisk.size()),
                () -> assertEquals(2, groupSubObjectsByRisk.get(Risk.of(Risk.Type.FIRE)).size()),
                () -> assertEquals(1, groupSubObjectsByRisk.get(Risk.of(Risk.Type.THEFT)).size())
        );
    }

    @Test
    void should_Return_Multiple_SubObjects() {
        //given
        var policy = new Policy("", Policy.Status.APPROVED,
                List.of(new PolicyObject("",
                        List.of(new SubObject("", 0.0, Risk.Type.FIRE),
                                new SubObject("", 0.0, Risk.Type.THEFT),
                                new SubObject("", 0.0, Risk.Type.FIRE)))));
        //when
        var subObjects = policy.getSubObjects();

        //then
        assertEquals(3, subObjects.size());
    }
}
