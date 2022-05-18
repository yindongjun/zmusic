package com.example.zmusic.handler;

import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public ErrorResponse bizExceptionHandler(BizException e) {
        return ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .trace(e.getStackTrace())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedExceptionHandler(AccessDeniedException e) {
        return ErrorResponse.builder()
                .code(ExceptionType.FORBIDDEN.getCode())
                .message(ExceptionType.FORBIDDEN.getMessage())
                .trace(e.getStackTrace())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
        });
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler(Exception e) {
        return ErrorResponse.builder()
                .code(ExceptionType.INNER_ERROR.getCode())
                .message(ExceptionType.INNER_ERROR.getMessage())
                .trace(e.getStackTrace())
                .build();
    }
}
