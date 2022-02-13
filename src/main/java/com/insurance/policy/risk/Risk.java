package com.insurance.policy.risk;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Risk {
    public enum Type {
        FIRE, THEFT
    }
    public Type type;

    public Risk(Type type) {
        this.type = type;
    }

    public static Risk of(Type type) {
        return switch (type) {
            case FIRE -> new FireRisk(Type.FIRE);
            case THEFT -> new TheftRisk(Type.THEFT);
        };
    }

    public abstract double compute(double sumInsured);

}

