package com.ohgiraffers.springmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// springboot 생성 시 기본적으로 생기는 Application, Main 메소드 실행하여 동작 실행
// 이 클래스가 존재하는 위치가 ComponentScane 범위가 된다.

@SpringBootApplication
// ㄴ 컴포넌트 스캔 등 여러가지 필요한 빈을 자동설정 하는 기능.
// Application 빈 스캔, 실행 X
public class Chap05SpringMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap05SpringMybatisApplication.class, args);
    }

}
