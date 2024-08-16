package com.flow.assignment.rule.service;

import com.flow.assignment.rule.dto.request.RequestRuleDto;
import com.flow.assignment.rule.dto.response.IpDto;
import com.flow.assignment.rule.entity.Rule;
import com.flow.assignment.rule.repository.RuleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public void getAllRules() {

    }

    public void searchRules() {

    }

    public void deleteRule() {

    }

    public void searchRulesByPeriod() {

    }

}
