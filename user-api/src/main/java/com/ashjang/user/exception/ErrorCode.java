package com.ashjang.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 회원가입
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 전화번호입니다."),

    // 로그인
    SIGNIN_FAILED(HttpStatus.BAD_REQUEST, "아이디와 비밀번호를 다시 확인해주세요."),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
