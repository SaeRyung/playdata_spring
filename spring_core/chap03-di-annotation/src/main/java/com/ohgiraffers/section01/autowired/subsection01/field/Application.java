package com.ohgiraffers.section01.autowired.subsection01.field;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        //북서비스 꺼내기
        BookService bookService
                = applicationContext.getBean("bookServiceField", BookService.class);

        /* 필드 주입의 경우 IoC 컨테이너 없이 테스트 하려고 하면 bookDAO 의존성 주입이 불가능해서
        * 아래 코드 수행시 NullPointerException 발생한다 => 생성자 주입 권장 */
        bookService.selectAllBooks().forEach(System.out::println); //모든목록
        System.out.println(bookService.searchBookBySequence(1)); //1번출력

    }
}
