package com.insurance.policy.risk;

public class FireRisk extends Risk {

    FireRisk(Risk.Type type) {
        super(type);
    }

    @Override
    public double compute(double sumInsured) {
        if (sumInsured > 100) {
            return sumInsured * 0.024;
        }
        return sumInsured * 0.014;
    }
}
