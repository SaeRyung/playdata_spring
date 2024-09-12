package com.ohgiraffers.jpql.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupFunctionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // count 경우 없는 코드 0 으로 출력
    public long countMenuOfCategory(int categoryCode){
        // :categoryCode : 넘어온 파라미터 값
        // 존재하는 m.menuCode 카운트
        String jpql = "SELECT COUNT(m.menuCode) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";
        long countOfMenu = entityManager.createQuery(jpql, Long.class)
                //정수값 > long타입
                .setParameter("categoryCode", categoryCode)
                .getSingleResult();

        return countOfMenu;
    }


    // count 외에 null이 반환
    // Long 타입 또는 Double로 반환하여 null 처리
    public Long SumMenuPriceOfCategory(int categoryCode){
        // :categoryCode : 넘어온 파라미터 값
        // 특정 카테고리 코드의 menuPrice를 sum 합니다.
        /* Count 외의 그룹 함수는 결과 값이 없을 경우 null 이 반환 되기 때문에
        * 기본 자료형으로 다룰 경우 문제가 생긴다. Wrapper 클래스 자료형으로 다루어 null 처리가 되도록 한다. */
        String jpql = "SELECT SUM(m.menuPrice) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";
        Long sumMenuPrice = entityManager.createQuery(jpql, Long.class)
                .setParameter("categoryCode", categoryCode)
                .getSingleResult();

        return sumMenuPrice;
    }



    // having절 jpql
    // 테스트로 보기 위해 타입 Object로 받음
    public List<Object[]> selectByGroupByHaving(long minPrice) {
        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice) FROM Section05Menu m "
        + "GROUP BY m.categoryCode HAVING SUM(m.menuPrice) >= :minPrice";

        return entityManager.createQuery(jpql).setParameter("minPrice", minPrice).getResultList();
    }
}
