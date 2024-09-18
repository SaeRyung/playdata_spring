package org.ohgiraffers.chap11.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/* 필터등록 web.xml(배포서술자)에서 처리한다.*/
public class EncodingFilter implements Filter {

    private String encodingType;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encodingType = filterConfig.getInitParameter("encoding-type");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // POST 방식의 요청일 경우 문자 셋을 설정하는 전처리 코드
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if("POST".equals(request.getMethod())) servletRequest.setCharacterEncoding(encodingType);

        filterChain.doFilter(servletRequest, servletResponse); // 다음 필터 또는 서블릿 요청
    }
}
