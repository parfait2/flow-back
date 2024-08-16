package com.flow.assignment.rule.repository;

import org.apache.tomcat.util.digester.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
