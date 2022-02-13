package com.insurance.policy.risk;

public class TheftRisk extends Risk {

    TheftRisk(Type type) {
        super(type);
    }

    @Override
    public double compute(double sumInsured) {
        if (sumInsured >= 15) {
            return sumInsured * 0.05;
        }
        return sumInsured * 0.11;
    }
}
