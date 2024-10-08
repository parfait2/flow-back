package com.flow.assignment.rule.controller;

import com.flow.assignment.rule.dto.request.PeriodDto;
import com.flow.assignment.rule.dto.request.RequestRuleDto;
import com.flow.assignment.rule.dto.response.IdDto;
import com.flow.assignment.rule.dto.response.IpDto;
import com.flow.assignment.rule.dto.response.RuleDto;
import com.flow.assignment.rule.service.RuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rules")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "IP 규칙 API", description = "허용된 IP 접근 제한 설정 기능")
public class RuleController {

    private final RuleService ruleService;

    /**
     * 현재 IP 불러오기
     * : 사용자의 IP 주소를 반환합니다.
     *
     * @return ResponseEntity<IpDto>
     * */
    @Operation(summary = "현재 IP 불러오기", description = "사용자의 IP 주소를 반환합니다.")
    @GetMapping("/ip")
    public ResponseEntity<IpDto> getUserIp() {
        return ResponseEntity.ok(ruleService.getUserIp());
    }

    /**
     * IP 규칙 등록
     * : IP 주소, 설명, 사용 허용 시간을 등록합니다.
     *
     * @param requestRuleDto
     * @return ResponseEntity<IdDto>
     * */
    @Operation(summary = "IP 규칙 등록", description = "IP 주소, 설명, 사용 허용 시간을 등록합니다.")
    @PostMapping()
    public ResponseEntity<IdDto> saveRule(@RequestBody @Valid RequestRuleDto requestRuleDto) {
//        UUID savedId = ruleService.save(requestRuleDto);
//        return ResponseEntity.created(URI.create("/api/rules/" + savedId)).build();
        return ResponseEntity.ok(ruleService.save(requestRuleDto));
    }

    /**
     * IP 규칙 전체 조회
     * : 모든 등록된 규칙들이 리스트 형식으로 반환됩니다.
     *
     * @param pageable
     * @return ResponseEntity<Page<RuleDto>>
     * */
    @Operation(summary = "IP 규칙 전체 조회", description = "모든 등록된 규칙들이 리스트 형식으로 반환됩니다.")
    @GetMapping()
    public ResponseEntity<Page<RuleDto>> getAllRules(Pageable pageable) {
        return ResponseEntity.ok(ruleService.getAllRules(pageable));
    }

    /**
     * 내용 검색
     * : IP 규칙 추가 시 작성하는 내용을 기준으로 검색합니다.
     *
     * @param content, pageable
     * @return ResponseEntity<Page<RuleDto>>
     * */
    @Operation(summary = "내용 검색", description = "IP 추가 시 작성하는 내용을 기준으로 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<Page<RuleDto>> searchRules(@RequestParam String content, Pageable pageable) {
        return ResponseEntity.ok(ruleService.searchRules(content, pageable));
    }

    /**
     * IP 규칙 삭제
     * : 삭제 클릭 시 해당 규칙을 삭제합니다.
     *
     * @param id
     * @return ResponseEntity<Void>
     * */
    @Operation(summary = "IP 규칙 삭제", description = "삭제 클릭 시 해당 규칙을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable UUID id) {
        ruleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 기간 검색
     * : 사용 시작 시간, 사용 끝 시간으로 검색합니다.
     *
     * @param periodDto, pageable
     * @return ResponseEntity<Page<RuleDto>>
     * */
    @Operation(summary = "기간 검색", description = "사용 시작 시간, 사용 끝 시간으로 검색합니다.")
    @PostMapping("/period")
    public ResponseEntity<Page<RuleDto>> searchRulesByPeriod(@RequestBody PeriodDto periodDto, Pageable pageable) {
        return ResponseEntity.ok(ruleService.searchRulesByPeriod(periodDto, pageable));
    }

}
