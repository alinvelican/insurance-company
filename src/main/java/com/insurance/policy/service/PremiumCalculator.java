package com.insurance.policy.service;

import com.insurance.policy.model.Policy;
import com.insurance.policy.model.PolicyCost;
import com.insurance.policy.model.Risk;
import com.insurance.policy.model.SubObject;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PremiumCalculator implements CostCalculator {
    @Override
    public PolicyCost calculate(Policy policy) {
        var cost = policy.groupSubObjectsByRisk().entrySet().stream()
                .map(entry -> {
                        var risk = Risk.of(entry.getKey());
                        var subObjectsCost = getSubObjectsCost(entry.getValue());
                        return risk.compute(subObjectsCost);
                    }
                )
                .reduce(0.0, Double::sum);

        return PolicyCost.of(cost);
    }

    private double getSubObjectsCost(List<SubObject> subObjects) {
        return subObjects.stream().map(SubObject::cost).reduce(0.0, Double::sum);
    }
}
