package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    private static EntityManagerFactory factory
            //static 으로 하나만 만든다.
            = Persistence.createEntityManagerFactory("jpatest");
    // persistence.xml 이름 : jpatest

    private EntityManagerFactoryGenerator(){}
    // 생성자 private = new EntityManagerFactoryGenerator 하고 싶지 않다는 뜻
    // EntityManagerFactory : 어플리케이션 당 하나 생성

    public static EntityManagerFactory getInstance(){
        return factory;
        // factory 값이 필요할 때만 getInstance 로 반환한다.
    }
}
