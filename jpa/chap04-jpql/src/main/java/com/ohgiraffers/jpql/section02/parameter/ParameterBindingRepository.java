package com.ohgiraffers.jpql.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

// 파라미터 전달받기

@Repository
public class ParameterBindingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // 바인딩 1. 이름을 이용하기
    public List<Menu> selectMenuByBindingName(String menuName){
        // :menuName -> 넘어갈 파라미터 값, 바인딩해줘야 한다.
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = :menuName";
        List<Menu> resultMenuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter("menuName", menuName)
                //.setParameter("menuName" 바인딩키값 , menuName 실제변수)
                .getResultList();

        return resultMenuList;
    }


    // 바인딩 2. 위치 이용하기
    public List<Menu> selectMenuByBindingPoint(String menuName){
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = ?1";
        // m.menuName = ?1 : 위치로서 바인딩하겠다.
        List<Menu> resultMenuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter(1, menuName)
                .getResultList();
        //.setParameter(1, 바인딩위치 , menuName 실제변수)
        return resultMenuList;
    }
}
