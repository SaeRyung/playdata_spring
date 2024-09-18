package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// 순수하게 SQL 구문을 이용한 쿼리

@Repository
public class NativeQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // 엔티티 취급 > 모든 컬럼 작성
    public Menu nativeQueryByResultType(int menuCode) {
        /* Native Query 수행 결과를 엔터티에 매핑 시키려면 모든 컬럼이 조회 되어야만 매핑이 가능하다.*/
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status "
                + "FROM tbl_menu WHERE menu_code = ?"; //물음표 위치
        Query nativeQuery = entityManager.createNativeQuery(query, Menu.class).setParameter(1, menuCode); //위치, 위치에 맞는 파라미터값
        // ㄴ 결과값 엔티티로 받고 싶다면 모든 컬럼 조회..
        return (Menu) nativeQuery.getSingleResult();
    }

    // 엔티티화 X, 컬럼이 특별한 타입이 아니므로 Object로 반환
    public List<Object[]> nativeQueryByNoResultType() {
        String query = "SELECT menu_name, menu_price FROM tbl_menu";
        Query nativeQuery = entityManager.createNativeQuery(query);
        return nativeQuery.getResultList();
    }

    // nativeQuery 매핑 원할 시 시
    public List<Object[]> nativeQueryByAutoMapping() {
        String query
                = "SELECT a.category_code, a.category_name, a.ref_category_code," +
                " COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category a" +
                /*ㄴ카테고리 테이블 기준 조인, 값이 없으면 0 처리: COALESCE(v.menu_count, 0)*/
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                " FROM tbl_menu b" +
                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                " ORDER BY 1";
                /* 서브쿼리,  특정 카테고리 해당하는 메뉴 개수 세기 */
        Query nativeQuery
                = entityManager.createNativeQuery(query, "categoryCountAutoMapping");
        // categoryCountAutoMapping -> Category.java
        return nativeQuery.getResultList();
    }

    //카테고리 해당 메뉴 몇갠지 조회, 매핑방식만 바꾼 코드
    public List<Object[]> nativeQueryByManualMapping() {
        String query
                = "SELECT a.category_code, a.category_name, a.ref_category_code," +
                " COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category a" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                " FROM tbl_menu b" +
                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                " ORDER BY 1";
        Query nativeQuery
                = entityManager.createNativeQuery(query, "categoryCountManualMapping");
        //categoryCountManualMapping > Category
        return nativeQuery.getResultList();
    }












}