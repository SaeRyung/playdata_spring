package com.ohgiraffers.section02.javaconfig;
// 의존성 주입 > 자바코드로
import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        // 이름 설정 안해주면 맨 처음으로 따라가기에 name: memberGenerator 메소드로 설정해줌
        MemberDTO member = applicationContext.getBean("memberGenerator", MemberDTO.class);
        System.out.println(member.getPersonalAccount().deposit(10000));
        System.out.println(member.getPersonalAccount().getBalance());
        System.out.println(member.getPersonalAccount().withDraw(5000));
        System.out.println(member.getPersonalAccount().getBalance());
        // Q. getPersonalAccount > PersonalAccount의 get함수?-------------------------
    }
}
