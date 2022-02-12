package com.insurance.policy.service;

import com.insurance.policy.model.Policy;
import com.insurance.policy.model.PolicyCost;

public interface CostCalculator {
    PolicyCost calculate(Policy policy);
}
