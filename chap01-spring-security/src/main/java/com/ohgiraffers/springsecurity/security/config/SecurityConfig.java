package com.ohgiraffers.springsecurity.security.config;

import com.ohgiraffers.springsecurity.security.filter.CustomAuthenticationFilter;
import com.ohgiraffers.springsecurity.security.filter.JwtFilter;
import com.ohgiraffers.springsecurity.security.handler.JwtAccessDeniedHandler;
import com.ohgiraffers.springsecurity.security.handler.JwtAuthenticationEntryPoint;
import com.ohgiraffers.springsecurity.security.handler.LoginFailureHandler;
import com.ohgiraffers.springsecurity.security.handler.LoginSuccessHandler;
import com.ohgiraffers.springsecurity.security.util.JwtUtil;
import com.ohgiraffers.springsecurity.service.UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //시큐리티 정의 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final Environment env;
    // user service 타입 변경 후 등록
    private final JwtUtil jwtUtil;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        /* CSRF 토큰 발행 시 Client 에서 매번 해당 토큰도 함께 요청에 넘겨주어야 하므로 기능 비활성화 */
        http.csrf(csrf -> csrf.disable())
                // spring security 다룰 때 필요로 하는 설정값 넣아준다.
                /* spring security 는 모든 요청을 가로채서 인증 되지 않았을 시에 401 에러,
                 * 일단 허용해서 회원가입 페이지 나올 수 있도록 설정한다. permitAll : 전부 허용한다. */
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers(new AntPathRequestMatcher("/users/**", "POST")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/users/**", "GET")).hasAnyAuthority("ADMIN")
                            .anyRequest().authenticated();
                })
                /* session 로그인 방식을 사용하지 않음 (JWT Token 방식을 사용할 예정) */
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );


        /* 커스텀 로그인 필터 이전에 JWT 토큰 확인 필터를 설정 */
        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        /* 커스텀 로그인 필터 추가 */
        //UsernamePasswordAuthenticationFilter 기본적으로 존재하는 인증 담당 필터
        // 원래 인증하는 기본필터 앞에 우리가 만든 것을 추가한다.
        http.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        /* 인증, 인가 실패 핸들러 설정 */
        http.exceptionHandling(
                exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(new JwtAccessDeniedHandler());
                    exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
                }
        );

        return http.build();
    }

    // 커스터마이징필터
    private Filter getAuthenticationFilter() {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        // 인증 대체하고자 사용하고자 하는 커스터마이징 필터
        customAuthenticationFilter.setAuthenticationManager(getAuthenticationManager());
        // 인증매니저 설정을 추가, 메소드가 반환하는 값을 통해
        customAuthenticationFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler(env));
        // 인증 성공 시 처리 커스터 마이징
        customAuthenticationFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        // 로그인 실패 핸들링
        return customAuthenticationFilter;
    }

    // AuthenticationManager : 인증을 전체적으로 매니징한다. 두가지 요소 설정
    /* UserDetailsService 타입 통해 loadUserByUsername 메서드 호출 -> 전달된 id값이 DB에서 일치하는 id있는지 존재 확인,
     * 조회 후 Security 다룰 수 있는 type으로 만들어 반환
     * 반환된 타입 pw가 맞는지 아닌지 확인 후 저장한다. */
    private AuthenticationManager getAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return new ProviderManager(provider);
    }
}
