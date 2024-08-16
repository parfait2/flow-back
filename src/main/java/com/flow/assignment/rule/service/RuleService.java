package com.flow.assignment.rule.service;

import com.flow.assignment.common.exception.CustomException;
import com.flow.assignment.common.exception.ErrorCode;
import com.flow.assignment.rule.dto.request.RequestRuleDto;
import com.flow.assignment.rule.dto.response.IpDto;
import com.flow.assignment.rule.dto.response.RuleDto;
import com.flow.assignment.rule.dto.response.RuleListDto;
import com.flow.assignment.rule.entity.Rule;
import com.flow.assignment.rule.repository.RuleRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RuleService {

    private final RuleRepository ruleRepository;
    private final HttpServletRequest request;

    public RuleService(RuleRepository ruleRepository, HttpServletRequest request) {
        this.ruleRepository = ruleRepository;
        this.request = request;
    }

    public IpDto getUserIp() {

        // TODO
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        return IpDto.builder()
                .ipAddress(ip)
                .build();
    }

    @Transactional
    public UUID save(RequestRuleDto requestRuleDto) {
        Rule savedRule = ruleRepository.save(Rule.of(
                requestRuleDto.getIpAddress(),
                requestRuleDto.getDescription(),
                requestRuleDto.getStartTime(),
                requestRuleDto.getEndTime()
        ));

        return savedRule.getId();
    }

    @Transactional(readOnly = true)
    public RuleListDto getAllRules() {

        // 리스트는 등록 시간 기준 내림차순으로 나열됩니다.
        List<Rule> rulesList = ruleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdTime"));

        List<RuleDto> ruleDtoList = rulesList.stream()
                .map(RuleDto::fromEntity)
                .collect(Collectors.toList());

        return new RuleListDto(ruleDtoList);
    }

    @Transactional(readOnly = true)
    public RuleListDto searchRules(String content) {
        List<Rule> ruleList = ruleRepository.searchByContent(content); // IP 주소 또는 설명에 해당하는 내용이 있으면 반환합니다.

        List<RuleDto> ruleDtoList = ruleList.stream()
                .map(RuleDto::fromEntity)
                .collect(Collectors.toList());

        return new RuleListDto(ruleDtoList);
    }

    @Transactional
    public void deleteRule(UUID id) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RULE));

        ruleRepository.delete(rule); // SOFT DELETE
    }

    public void searchRulesByPeriod() {

    }

}
