package com.insurance.policy.model;

public class FireRisk implements Risk {
    @Override
    public double compute(double sumInsured) {
        if (sumInsured > 100) {
            return sumInsured * 0.024;
        }
        return sumInsured * 0.014;
    }
}
