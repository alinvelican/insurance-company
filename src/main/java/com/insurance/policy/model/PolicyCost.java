package com.insurance.policy.model;

public record PolicyCost(double cost) {
    public static PolicyCost of(double cost) {
        return new PolicyCost(cost);
    }
}
