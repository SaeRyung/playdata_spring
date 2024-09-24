package com.ohgiraffers.springsecurity.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.springsecurity.security.dto.LoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.ArrayList;

// 시큐리티 사용되는 형식이어야 하므로 상속, 인증관련 필터
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /* 해당 요청이 올 때 이 필터가 동작하도록 설정한다. */
    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    /* 필터 동작 시 수행할 코드 작성하는 메소드*/
    // 인증 토큰 만들어 준다. id, pwd 기반,,request 안에 있다. -> id, pwd 를 읽어온 후
    //1. UsernamePasswordAuthenticationToken 토큰 만들어서 넣어주기
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        /* request body에 담긴 정보를 우리가 만든 LoginRequest 타입에 담아준다. */
        // @RequestBody 에서 자동으로 하는 작업이지만 ObjectMapper로 직접 설정한다.
        /* Controller의 @RequestBody 어노테이션을 통해 자동으로 convert 되었던 부분을 filter에서 직접 처리하는 과정 */
        LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        // 2. AuthenticationManager 만들어서 셋팅
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUserId(), creds.getPwd(), new ArrayList<>())
                // 인증 토큰으로 바꿔서 UserService로 넘겨준다. -> loadUserByUsername
        );
    }
}
