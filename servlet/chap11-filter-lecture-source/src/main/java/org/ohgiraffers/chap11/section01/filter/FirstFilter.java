package org.ohgiraffers.chap11.section01.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/first/*")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화
        System.out.println("Filter init 호출");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /* 서블릿 호출 전 처리 코드 */
        System.out.println("Filter doFilter 호출 - 전처리");

        /* 필터에서 전처리가 끝나면 다음 필터 또는 서블릿을 호출하는 코드이다. */
        // doFilter 기준 전처리, 후처리
        filterChain.doFilter(servletRequest, servletResponse);

        /* 서블릿 호출 후 처리 코드 */
        System.out.println("서블릿 요청 수행 완료 - 후처리");

    }

    @Override
    public void destroy() {
        // 소멸
        System.out.println("Filter destroy 호출");
    }
}
