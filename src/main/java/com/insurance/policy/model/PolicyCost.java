package com.insurance.policy.model;

public record PolicyCost(double policyCost, String policyNumber, Policy.Status policyStatus) {

    public static PolicyCost of(double cost, Policy policy) {
        return new PolicyCost(cost, policy.number(), policy.status());
    }
}
