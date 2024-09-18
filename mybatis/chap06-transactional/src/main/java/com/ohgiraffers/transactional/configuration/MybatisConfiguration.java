package com.ohgiraffers.transactional.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ohgiraffers.transactional", annotationClass = Mapper.class)
// @MapperScan: 하위 패키지 인터페이스를 매퍼로 지정하는 기능
public class MybatisConfiguration {
}
