package com.flow.assignment.rule.repository;

import com.flow.assignment.rule.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
