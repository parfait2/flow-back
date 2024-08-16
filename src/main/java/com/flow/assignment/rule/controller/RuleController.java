package com.flow.assignment.rule.controller;

import com.flow.assignment.rule.dto.request.RequestRuleDto;
import com.flow.assignment.rule.service.RuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rule")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "IP 규칙 API", description = "허용된 IP 접근 제한 설정 기능")
public class RuleController {

    private final RuleService ruleService;

    /**
     * IP 규칙 등록
     * : IP 주소, 설명, 사용 허용 시간을 등록합니다.
     *
     * @param
     * @return
     * */
    @Operation(summary = "IP 규칙 등록", description = "IP 주소, 설명, 사용 허용 시간을 등록합니다.")
    @PutMapping("/save")
    public ResponseEntity<?> saveRule(@RequestBody RequestRuleDto requestRuleDto) {
        return null;
    }

    /**
     * IP 규칙 전체 조회
     * : 모든 등록된 규칙들이 리스트 형식으로 반환됩니다.
     *
     * @param
     * @return
     * */
    @Operation(summary = "규칙 전체 조회", description = "모든 등록된 규칙들이 리스트 형식으로 반환됩니다.")
    @GetMapping()
    public ResponseEntity<?> getAllRules() {
        return null;
    }

    /**
     * 내용 검색
     * : IP 규칙 추가 시 작성하는 내용을 기준으로 검색합니다.
     *
     * @param
     * @return
     * */
    @Operation(summary = "내용 검색", description = "IP 추가 시 작성하는 내용을 기준으로 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<?> searchRules() {
        return null;
    }

    /**
     * IP 규칙 삭제
     * : 삭제 클릭 시 해당 규칙을 삭제합니다.
     *
     * @param
     * @return
     * */
    @Operation(summary = "IP 규칙 삭제", description = "삭제 클릭 시 해당 규칙을 삭제합니다.")
    @GetMapping("/delete")
    public ResponseEntity<?> deleteRule() {
        return null;
    }

    /**
     * 기간 검색
     * : 사용 시작 시간, 사용 끝 시간으로 검색합니다.
     *
     * @param
     * @return
     * */
    @Operation(summary = "기간 검색", description = "사용 시작 시간, 사용 끝 시간으로 검색합니다.")
    @GetMapping("/period")
    public ResponseEntity<?> searchRulesByPeriod() {
        return null;
    }

}
