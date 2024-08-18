package com.flow.assignment.rule.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
public class IdDto {
    private UUID id;
}
