package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ConfigurationContext.class);
        //클래스에 ConfigurationContext.class 메타정보 전달

        MemberDTO member = context.getBean("member", MemberDTO.class);
        System.out.println(member);
    }
}
