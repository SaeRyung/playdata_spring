package com.ohgiraffers.requestmapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: 설정파일로서 쓰일 수 있다. 여러가지 기능 자동 활성화, ComponentScan 활성화.
// 기본설정이 들어가있기에 우리는 POJO 파일만 작성
@SpringBootApplication
public class Chap01RequestMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap01RequestMappingApplication.class, args);
    }
    // SpringApplication.run: main 메소드를 통해 application 구동시킴

}
