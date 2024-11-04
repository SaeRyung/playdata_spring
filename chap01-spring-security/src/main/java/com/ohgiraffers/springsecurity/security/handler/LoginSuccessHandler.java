package com.ohgiraffers.springsecurity.security.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    // springframwork.core.env.Environment
    private final Environment env;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("로그인 성공 후 security가 관리하는 principal 객체 : {}", authentication);

        /* 권한을 꺼내 List<String> 으로 변환 */
        // 권한 여러개 있을 수 있으므로 List 타입으로 구현
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        /* Token에 들어갈 Claim 생성 */
        Claims claims = Jwts.claims().setSubject(authentication.getName()); //정의된값
        claims.put("auth", authorities); //정의되지 않은 사용자값

        // jwts 라이브러리 추가, builder 호출 : 문자열로 토큰 만든다.
        String token = Jwts.builder()
                .setClaims(claims) //body 영역 : 권한 관련 정보
                .setExpiration(
                        new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("spring.token.expiration_time")))
                )  // 현재 시간 + 만료시간
                .signWith(
                        SignatureAlgorithm.HS512, env.getProperty("token.secret")
                ) //서명: 서명 알고리즘, 그 때 사용될 비밀키
                .compact();

        // 성공 후 응답
        response.setHeader("token", token);
    }
}
