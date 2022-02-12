package com.insurance.policy.model;



public interface Risk {
    enum Type {
        FIRE, THEFT
    }
    public static Risk of(Type type) {
        return switch (type) {
            case FIRE -> new FireRisk();
            case THEFT -> new TheftRisk();
        };
    }

    double compute(double sumInsured);

}

