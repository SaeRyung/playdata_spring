package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
//springboot는 ioc 환경이므로 Bean 등록

// Repository : DB에 접근하는 모든 코드가 모여있다.
// Service : 비즈니스 로직 관련 코드

@Repository
public class MemberRepository {

    @PersistenceContext //엔티티 매니저 의존성주입 annotation
    private EntityManager entityManager;

    public void save(Member member){
        entityManager.persist(member);
    }


    // memberId 기준으로 객체를 받아오는 기능, 이름 받아오기
    public String findNameById(String memberId){
        // 별칭 반드시 필요하다. 필드명 들어감
        String jpql = "SELECT m.memberName FROM entityMember m WHERE m.memberId = '" + memberId + "'";
        return entityManager.createQuery(jpql, String.class).getSingleResult();
        //  jpql 문자 넣기, String.class=반환값 타입 / 문자열로 반환,
        // 아이디 전달해서 이름을 반환받는다
    }
}
