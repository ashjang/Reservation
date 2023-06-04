package com.ashjang.review.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ReviewExceptionAdvice {
    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<CustomException.CustomExceptionResponse> exceptionHandler(HttpServletRequest req, CustomException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(
                        CustomException.CustomExceptionResponse.builder()
                                .status(exception.getStatus())
                                .code(exception.getErrorCode().name())
                                .message(exception.getMessage())
                                .build()
                );
    }
}
