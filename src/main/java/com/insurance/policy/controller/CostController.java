package com.insurance.policy.controller;

import com.insurance.policy.model.Policy;
import com.insurance.policy.model.PolicyCost;
import com.insurance.policy.service.CostCalculator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class CostController {

    CostCalculator calculator;

    @PostMapping("/compute-cost")
    PolicyCost computeCost(@RequestBody Policy policy) {
        return calculator.calculate(policy);
    }
}
