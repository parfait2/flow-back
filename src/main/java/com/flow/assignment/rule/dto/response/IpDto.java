package com.flow.assignment.rule.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class IpDto {
    private String ipAddress;
}
