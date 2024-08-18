package com.flow.assignment.rule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RuleListDto {
    private List<RuleDto> ruleList;
}
