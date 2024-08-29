package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("configurationSection03")
/* 베이스 패키지 설정이 별도로 없을 경우 현재 패키지 기준으로 스캔이 수행 된다.*/
@ComponentScan(basePackages = "com.ohgiraffers")
// ComponentScan: 스캔해서 빈으로 등록해주세요,
// basePackage 기준으로 하위에 있는 모든 패키지가 스캔된다.
public class ConfigurationContext {
}
