package com.example.zmusic.handler;

import cn.hutool.json.JSONUtil;
import com.example.zmusic.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ErrorResponse errorResp =
                ErrorResponse.builder()
                        .code(ExceptionType.FORBIDDEN.getCode())
                        .message(ExceptionType.FORBIDDEN.getMessage())
                        .build();
        PrintWriter writer = response.getWriter();
        writer.println(JSONUtil.toJsonStr(errorResp));
        writer.flush();
    }
}
