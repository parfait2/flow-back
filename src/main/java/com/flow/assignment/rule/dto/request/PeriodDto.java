package com.flow.assignment.rule.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class PeriodDto {

    private LocalDateTime startTime; // 사용 허용 시작 시간

    private LocalDateTime endTime; // 사용 허용 종료 시간
}
