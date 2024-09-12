package com.ohgiraffers.jpql.section01.simple;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

// 직접 조회값을 리터럴로 작성하여 조회

@Repository
public class SimpleJPQLRepository {


    /*
    createQuery(첫번째인자, 두번째인자)
    첫번째 인자 : 수행할 객체지향 쿼리
    두번째 인자 : 반환타입
    * */


    @PersistenceContext
    private EntityManager entityManager;

    // 엔티티 항상 별칭 필수로 작성해야한다.
    // 반환 String
    public String selectSingleMenuTypedQuery() {
        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = 8";
        // 필드명 작성, m.menuCode=8 인 값 찾아온다. 찾아오는 요소는 m.menuName 하는 문자열값
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        // ㄴString값 받을 형태로 지정 / (문자열-수행할 구문, 반환받을타입)
        String resultMenuName = query.getSingleResult();
        return resultMenuName;
    }


    // 반환 타입 Object
    public Object selectSingleMenuQuery() {
        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = 8";
        // 필드명 작성, m.menuCode=8 인 값 찾아온다. 찾아오는 요소는 m.menuName 하는 문자열값
        Query query = entityManager.createQuery(jpql);
        // ㄴ jpql 수행할 구문 전달하나 반환타입 전달하지 않으면 일반Query 객체로 반환된다.
        Object resultMenuName = query.getSingleResult();
        // ㄴ 위 구문에서 타입 지정하지 않았기에 Object로 반환된다.
        return resultMenuName;
    }


    // 반환 타입 엔티티
    public Menu selectSingleRowMenuQuery() {
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.menuCode = 8";
        // 엔티티 m : 조회해오는 대상 엔티티로 조회하겠다. > 반환값은 엔티티 자체로 된다.
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        Menu resultMenu = query.getSingleResult();
        // 하나 결과 받을 경우 getSingleResult
        return resultMenu;
    }


    // 반환 타입 엔티티
    public List<Menu> selectMultiRowByTypedQuery() {
        String jpql = "SELECT m FROM Section01Menu as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultMenuList = query.getResultList();
        // 여러개 결과 받을 경우 getResultList
        return resultMenuList;
    }




    ////////////////////////연산자를 사용한 조회

    // DISTINCT : 중복제거조회
    public List<Integer> selectUsingDistinct() {
        String jpql = "SELECT DISTINCT m.categoryCode FROM Section01Menu as m";
        // 중복 제거 조회
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        // 카테고리 코드가 반환되기 때문에 타입 : Integer
        List<Integer> resultCategoryCodeList = query.getResultList();
        return resultCategoryCodeList;
    }

    // 11, 12 카테고리 코드를 가진 메뉴 목록 조회
    public List<Menu> selectUsingIn() {
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.categoryCode IN (11, 12)";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultMenuCodeList = query.getResultList();
        return resultMenuCodeList;
         }
    // "마늘" 이라는 문자열이 메뉴명에 포함 되는 메뉴 목록 조회
    public List<Menu> selectUsingLike() {
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.menuName LIKE '%마늘%'";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultMenuLikeName = query.getResultList();
        return resultMenuLikeName;
    }

}
