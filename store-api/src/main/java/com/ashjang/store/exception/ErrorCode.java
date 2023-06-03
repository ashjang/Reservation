package com.ashjang.store.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 권한 없음
    NO_ACCESS_USER(HttpStatus.BAD_REQUEST, "접근 권한이 없습니다."),

    // 상점
    ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이름입니다."),
    NOT_FOUND_STORE(HttpStatus.BAD_REQUEST, "등록된 상점이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
