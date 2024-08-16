package com.flow.assignment.rule.repository;

import com.flow.assignment.rule.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    @Query("select r from Rule r where r.address like %:content% or r.description like %:content%")
    List<Rule> searchByContent(@Param("content") String content);
}
