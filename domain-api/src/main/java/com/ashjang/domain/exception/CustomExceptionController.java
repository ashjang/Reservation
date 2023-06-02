package com.ashjang.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionController {
    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<ExceptionResponse> customerRequestException(final CustomException customException) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(customException.getMessage(), customException.getErrorCode()));
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class ExceptionResponse {
        private String message;
        private ErrorCode errorCode;
    }
}
