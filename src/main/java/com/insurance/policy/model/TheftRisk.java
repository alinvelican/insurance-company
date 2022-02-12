package com.insurance.policy.model;

public class TheftRisk implements Risk {
    @Override
    public double compute(double sumInsured) {
        if (sumInsured >= 15) {
            return sumInsured * 0.05;
        }
        return sumInsured * 0.11;
    }
}
