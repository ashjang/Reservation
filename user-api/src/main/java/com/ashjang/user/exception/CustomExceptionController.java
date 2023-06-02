package com.ashjang.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionController {
    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<CustomException.CustomExceptionResponse> customerRequestException(final CustomException exception) {
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
