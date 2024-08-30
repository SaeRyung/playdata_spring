package com.ohgiraffers.section03.annotationconfig.subsection02.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                //GenericXmlApplicationContext: 전달하는 메타데이터가 xml 형식이다.
                = new GenericXmlApplicationContext("section03/annotationconfig/spring-context.xml");

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : beanNames) {
            System.out.println("beanName: " + beanName);
        }
    }
}
