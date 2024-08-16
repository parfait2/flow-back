package com.flow.assignment.rule.entity;

import com.flow.assignment.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "rule")
@Entity
public class Rule extends BaseEntity {

    @Id
    private Long id;

    private String address; // IP 주소

    private String description; // 설명(최대 20자 입력 가능)

    private LocalDateTime startTime; // 사용 허용 시작 시간

    private LocalDateTime endTime; // 사용 허용 종료 시간

}
