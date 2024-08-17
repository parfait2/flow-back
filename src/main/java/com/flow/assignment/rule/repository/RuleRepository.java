package com.flow.assignment.rule.repository;

import com.flow.assignment.rule.entity.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RuleRepository extends JpaRepository<Rule, UUID> {

    @Query("select r from Rule r where r.address like %:content% or r.description like %:content%")
    Page<Rule> searchByContent(@Param("content") String content, Pageable pageable);

    Page<Rule> findAllByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
}
