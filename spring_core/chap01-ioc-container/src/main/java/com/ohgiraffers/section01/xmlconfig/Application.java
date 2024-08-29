package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("section01/xmlconfig/spring-context.xml");

        // 전부 같은 객체. 가지고 오기 확인 용도
        MemberDTO member1 = (MemberDTO) applicationContext.getBean("member"); //ioc에 등록된 bean 확인 , bean id
        MemberDTO member2 = applicationContext.getBean(MemberDTO.class); // 클래스 메타 정보, 타입 정해졌기에 다운캐스팅X
        MemberDTO member3 = applicationContext.getBean("member", MemberDTO.class);

        System.out.println(member1);
        System.out.println(member2);
        System.out.println(member3);
    }
}
