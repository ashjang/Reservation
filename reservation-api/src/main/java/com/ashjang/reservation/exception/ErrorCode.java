package com.ashjang.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NO_ACCESS_USER(HttpStatus.BAD_REQUEST, "접근 권한이 없습니다."),
    NO_ACCESS(HttpStatus.BAD_REQUEST, "접근이 불가능합니다."),
    CANNOT_RESERVE(HttpStatus.BAD_REQUEST, "예약이 불가능합니다. 매장에 문의해 주시기 바랍니다."),
    CANNOT_VERIFY(HttpStatus.BAD_REQUEST, "확인 가능한 예약이 없습니다."),
    NOT_CORRECT_RESERVE(HttpStatus.BAD_REQUEST, "예약이 올바르지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
