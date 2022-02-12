package com.insurance.policy.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Policy(String number, Status status, List<PolicyObject> objects) {

    public List<SubObject> getSubObjects() {
        return objects.stream()
                .flatMap(obj -> obj.subObjects().stream()).toList();
    }

    public Map<Risk.Type, List<SubObject>> groupSubObjectsByRisk() {
        return getSubObjects().stream()
                .collect(Collectors.groupingBy(SubObject::riskType, Collectors.toList()));
    }
}
