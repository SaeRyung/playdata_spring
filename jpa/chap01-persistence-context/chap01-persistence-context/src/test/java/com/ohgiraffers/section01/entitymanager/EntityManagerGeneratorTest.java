package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerGeneratorTest {

    @Test
    @DisplayName("엔터티 매니저 팩토리 생성 확인")
    void testGenerateEntityManagerFactory(){
        // given
        // when
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

        // then
        assertNotNull(factory);
    }

    // 싱글톤 확인 코드
    @Test
    @DisplayName("엔터티 매니저 팩토리 싱글톤 인스턴스 확인")
    void testIsEntityManagerFactorySingletonInstance(){
        //given
        //when
        EntityManagerFactory factory1 = EntityManagerFactoryGenerator.getInstance();
        EntityManagerFactory factory2 = EntityManagerFactoryGenerator.getInstance();

        //then
        assertEquals(factory1, factory2); //true
        assertEquals(factory1.hashCode(), factory2.hashCode()); //true
        // 값이 같은지 확인하여 싱글톤인지 확인
        // EntitiyManagerFactory 는 싱글톤 패턴으로 관리된다.

    }


    @Test
    @DisplayName("엔터티 매니저 생성 확인")
    void testGenerateEntityManager(){
        //given
        //when
        EntityManager entityManager = EntityManagerGenerator.getInstance();

        //then
        assertNotNull(entityManager);
        // ㄴ 생성여부 notnull로 확인

    }

    @Test
    @DisplayName("엔터티 매니저 스코프 확인")
    void testEntityManagerLifeCycle(){
        //given
        //when
        EntityManager entityManager1 = EntityManagerGenerator.getInstance();
        EntityManager entityManager2 = EntityManagerGenerator.getInstance();

        //then
        assertNotEquals(entityManager1, entityManager2); //true
        assertNotEquals(entityManager1.hashCode(), entityManager2.hashCode()); //true

        // EntityManager는 일반적으로 스프링에서 싱글톤 X, 각 요청마다 새로운 인스턴스 생성된다.
    }
}
