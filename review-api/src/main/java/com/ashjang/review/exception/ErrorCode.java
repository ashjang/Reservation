package com.ashjang.review.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NO_ACCESS_USER(HttpStatus.BAD_REQUEST, "접근 권한이 없습니다."),
    CANNOT_REVIEW(HttpStatus.BAD_REQUEST, "리뷰 가능한 예약이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
