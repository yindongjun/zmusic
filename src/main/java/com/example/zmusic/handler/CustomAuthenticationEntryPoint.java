package com.example.zmusic.handler;

import cn.hutool.json.JSONUtil;
import com.example.zmusic.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorResponse errorResp = ErrorResponse.builder()
                .code(ExceptionType.UNAUTHORIZED.getCode())
                .message(ExceptionType.UNAUTHORIZED.getMessage())
                .build();
        PrintWriter writer = response.getWriter();
        writer.println(JSONUtil.toJsonStr(errorResp));
        writer.flush();
    }
}
