package com.ohgiraffers.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01.aop");
        // 이름 지정하지 않았기에 memberService > 앞글자 소문자로
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        System.out.println("========== selectMembers ==========");
        System.out.println(memberService.selectMembers()); //핵심기능 호출 맴버 조회
        System.out.println("======== selectMember ========");
        System.out.println(memberService.selectMember(1L)); //핵심기능 맴버 1번 조회
    }
}
