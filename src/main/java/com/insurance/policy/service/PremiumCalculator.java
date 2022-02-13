package com.insurance.policy.service;

import com.insurance.policy.model.Policy;
import com.insurance.policy.model.PolicyCost;
import com.insurance.policy.risk.Risk;
import com.insurance.policy.model.SubObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PremiumCalculator implements CostCalculator {
    @Override
    public PolicyCost calculate(Policy policy) {
        var subObjectsGroupedByRisk = policy.groupSubObjectsByRisk();

        var cost = subObjectsGroupedByRisk.entrySet().stream()
                .map(this::getRiskCost)
                .mapToDouble(d -> d)
                .sum();

        return PolicyCost.of(cost, policy);
    }

    private double getRiskCost(Map.Entry<Risk, List<SubObject>> riskSubObjectsPair) {
        var risk = riskSubObjectsPair.getKey();
        var subObjects = riskSubObjectsPair.getValue();
        var subObjectsCost = getSubObjectsCost(subObjects);

        return risk.compute(subObjectsCost);
    }

    private double getSubObjectsCost(List<SubObject> subObjects) {
        return subObjects.stream()
                .map(SubObject::getCost)
                .mapToDouble(d -> d)
                .sum();
    }
}
