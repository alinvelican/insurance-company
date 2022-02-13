package com.insurance.policy.model;


import com.insurance.policy.risk.Risk;
import lombok.Data;

@Data
public class SubObject {
    String name;
    double cost;
    Risk risk;

    public SubObject(String name, double cost, Risk.Type riskType) {
        this.name = name;
        this.cost = cost;
        this.risk = Risk.of(riskType);
    }
}
