package com.ashjang.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 전화번호입니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
