package com.ohgiraffers.section01.xmlconfig;
//의존성 주입 > xml
import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new GenericXmlApplicationContext("section01/xmlconfig/spring-context.xml"); //설정 메타정보 넘김
        // member 빈은 account빈을 setter로 의존성주입을 받은 상태로 참조.
        // account 처리 시 실행 잘 된다.

        MemberDTO member = applicationContext.getBean("member", MemberDTO.class);
        System.out.println(member.getPersonalAccount().deposit(10000));
        System.out.println(member.getPersonalAccount().getBalance());
        System.out.println(member.getPersonalAccount().withDraw(5000));
        System.out.println(member.getPersonalAccount().getBalance());
    }
}
