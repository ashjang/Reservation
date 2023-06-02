package com.ashjang.domain.exception;

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

    // 권한 없음
    NO_ACCESS_USER(HttpStatus.BAD_REQUEST, "접근 권한이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
