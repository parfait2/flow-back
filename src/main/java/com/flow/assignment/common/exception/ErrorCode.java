package com.flow.assignment.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat
public enum ErrorCode {

	INVALID_IP_ADDRESS(400, "I001", "잘못된 IP 형식입니다."),
	INVALID_PERIOD(400, "I002", "잘못된 기간 설정입니다."),
	INVALID_PAGE_REQUEST(400, "I003", "잘못된 페이지 요청입니다."),
	INVALID_SEARCH(400, "I004", "잘못된 검색어입니다."),

	NOT_FOUND_RULE(404, "N001", "존재하지 않는 IP 규칙입니다."),
	NO_RULES_FOUND(404, "N002", "IP 규칙이 존재하지 않습니다."),

	DELETE_RULE_FAILED(404, "D001", "이미 삭제된 규칙입니다.")
	;

	private int status;
	private String code;
	private String message;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;

	}

}
