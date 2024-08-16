package com.flow.assignment.rule.service;

import com.flow.assignment.rule.repository.RuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RuleService {

    private final RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void saveRule() {

    }

    public void getAllRules() {

    }

    public void searchRules() {

    }

    public void deleteRule() {

    }

    public void searchRulesByPeriod() {

    }

}
