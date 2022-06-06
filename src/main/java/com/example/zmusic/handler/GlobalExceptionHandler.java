package com.example.zmusic.handler;

import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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
    public List<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors()
                .stream()
                .map(error -> ErrorResponse.builder()
                        .code(ExceptionType.BAD_REQUEST.getCode())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e) {
        return ErrorResponse.builder()
                .code(ExceptionType.INNER_ERROR.getCode())
                .message(ExceptionType.INNER_ERROR.getMessage())
                .trace(e.getStackTrace())
                .build();
    }
}
