package com.flow.assignment.rule.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class RequestRuleDto {

    private String ipAddress; // 허용할 ip 주소

    private String description; // 설명(최대 20자 입력 가능)

    private LocalDateTime startTime; // 사용 허용 시작 시간

    private LocalDateTime endTime; // 사용 허용 종료 시간

}
