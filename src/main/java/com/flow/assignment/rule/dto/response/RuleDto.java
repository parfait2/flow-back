package com.flow.assignment.rule.dto.response;

import com.flow.assignment.rule.entity.Rule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDto {

    private String ipAddress;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static RuleDto fromEntity(Rule rule) {
        return RuleDto.builder()
                .ipAddress(rule.getAddress())
                .description(rule.getDescription())
                .startTime(rule.getStartTime())
                .endTime(rule.getEndTime())
                .build();
    }
}
