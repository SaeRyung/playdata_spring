package com.ohgiraffers.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// StopWatchInterceptor(intercept) 클래스 등록,, 인터셉터 추가 클래스

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    //2. 의존성 주입 선언
    private final StopWatchInterceptor stopWatchInterceptor;

    //3. 생성자 통해 stopwatch 주입한다.
    // @Autowird 생략 > 생성자 통한 의존성 주입시 생략 가능
    public WebConfiguration(StopWatchInterceptor stopWatchInterceptor) {
        this.stopWatchInterceptor = stopWatchInterceptor;
    }

    // 인터셉터 관리 객체
    //1.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 주입받은 생성자 인터셉트에 주입 => stopWatchInterceptor
        registry.addInterceptor(stopWatchInterceptor).addPathPatterns("/stopwatch");
        //stopWatchInterceptor 요청이 있을때 registry.addInterceptor > 이 인터셉터(stopwatch)를 추가해주세요
    }
}
