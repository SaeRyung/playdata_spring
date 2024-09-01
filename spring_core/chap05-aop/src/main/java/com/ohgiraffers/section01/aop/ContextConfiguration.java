package com.ohgiraffers.section01.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) //라이브러리 동작할 수 있도록 활성화, CGLib 방식을 통해 proxy 생성, 프록시 활성화
//@EnableAspectJAutoProxy : 부가적인 기능 로그로 찍힐 수 있도록 하는 어노테이션
public class ContextConfiguration {
}
