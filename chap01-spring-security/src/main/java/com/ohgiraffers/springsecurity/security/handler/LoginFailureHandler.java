package com.ohgiraffers.springsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.springsecurity.security.dto.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    // 실패 시 상황 반드시 구현해야 한다. exception
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        // writeValueAsString : 자바객체를
        response.getWriter().write(new ObjectMapper().writeValueAsString(new ExceptionResponse(401, "로그인 실패")));
    }
}
