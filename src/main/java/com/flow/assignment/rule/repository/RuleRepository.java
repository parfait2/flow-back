package com.flow.assignment.rule.repository;

import com.flow.assignment.rule.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RuleRepository extends JpaRepository<Rule, UUID> {

    @Query("select r from Rule r where r.address like %:content% or r.description like %:content%")
    List<Rule> searchByContent(@Param("content") String content);

    List<Rule> findAllByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
