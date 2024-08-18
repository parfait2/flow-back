package com.flow.assignment.rule.service;

import com.flow.assignment.common.exception.CustomException;
import com.flow.assignment.common.exception.ErrorCode;
import com.flow.assignment.rule.dto.request.PeriodDto;
import com.flow.assignment.rule.dto.request.RequestRuleDto;
import com.flow.assignment.rule.dto.response.IpDto;
import com.flow.assignment.rule.dto.response.RuleDto;
import com.flow.assignment.rule.entity.Rule;
import com.flow.assignment.rule.repository.RuleRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean isValidIp(String ip) {
        String ipPattern =
                "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

        return ip != null && ip.matches(ipPattern);
    }

    public IpDto getUserIp() {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && !ip.isEmpty()) {
            ip = ip.split(",")[0].trim(); // X-Forwarded-For 헤더가 여러 개의 IP 주소를 포함할 수 있습니다.
        } else {
            ip = request.getRemoteAddr();
        }

        if (!isValidIp(ip)) {
            throw new CustomException(ErrorCode.INVALID_IP_ADDRESS); // IP 주소 형식에 맞지 않으면 예외를 처리합니다.
        }

        return IpDto.builder()
                .ipAddress(ip)
                .build();
    }

    @Transactional
    public UUID save(RequestRuleDto requestRuleDto) {

        // 입력 값이 유효한 지 검증합니다.
        if (requestRuleDto.getIpAddress() == null || !isValidIp(requestRuleDto.getIpAddress())) {
            throw new CustomException(ErrorCode.INVALID_IP_ADDRESS); // IP 주소 형식에 맞지 않으면 예외를 처리합니다.
        }
        if (requestRuleDto.getStartTime().isAfter(requestRuleDto.getEndTime())) {
            throw new CustomException(ErrorCode.INVALID_PERIOD); // 사용 허용 시작 시간보다 끝 시간이 이전에 있으면 예외를 처리합니다.
        }

        Rule savedRule = ruleRepository.save(Rule.of(
                requestRuleDto.getIpAddress(),
                requestRuleDto.getDescription(),
                requestRuleDto.getStartTime(),
                requestRuleDto.getEndTime()
        ));

        return savedRule.getId();
    }

    @Transactional(readOnly = true)
    public Page<RuleDto> getAllRules(Pageable pageable) {
        Pageable sortedByCreatedAtDesc = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("createdTime").descending()
        );

        Page<Rule> rulesPage = ruleRepository.findAll(sortedByCreatedAtDesc);
        Page<RuleDto> ruleDtoPage = rulesPage.map(RuleDto::fromEntity);

        return ruleDtoPage;
    }

    @Transactional(readOnly = true)
    public Page<RuleDto> searchRules(String content, Pageable pageable) {
        Page<Rule> rulesPage = ruleRepository.searchByContent(content, pageable);

        return rulesPage.map(RuleDto::fromEntity);
    }

    @Transactional
    public void deleteRule(UUID id) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RULE));

        ruleRepository.delete(rule); // SOFT DELETE
    }

    public Page<RuleDto> searchRulesByPeriod(PeriodDto periodDto, Pageable pageable) {
        Page<Rule> rulesPage = ruleRepository.findAllByStartTimeBetween(periodDto.getStartTime(), periodDto.getEndTime(), pageable);

        return rulesPage.map(RuleDto::fromEntity);
    }

}
