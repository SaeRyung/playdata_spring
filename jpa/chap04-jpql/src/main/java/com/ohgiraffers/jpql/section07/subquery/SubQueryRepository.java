package com.ohgiraffers.jpql.section07.subquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // 한식이 전달되었을 때 한식 기준으로 카테고리 테이블 조회,
    // 조회한 테이블 조건으로 쓸 수 있도록 서브쿼리
    public List<Menu> selectWithSubQuery(String categoryName){
        String jpql = "SELECT m FROM Section07Menu m WHERE m.categoryCode = (" +
                "SELECT c.categoryCode FROM Section07Category c WHERE c.categoryName = :categoryName)";

        return entityManager.createQuery(jpql, Menu.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }
}
