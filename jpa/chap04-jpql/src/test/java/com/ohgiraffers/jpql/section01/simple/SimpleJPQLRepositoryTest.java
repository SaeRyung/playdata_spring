package com.ohgiraffers.jpql.section01.simple;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class SimpleJPQLRepositoryTest {

    @Autowired
    SimpleJPQLRepository simpleJPQLRepository;




    @DisplayName("TypeQuery를 이용한 단일행, 단일 컬럼 조회")
    @Test
    void testSelectSingleMenuByTypeQuery(){
        String menuName = simpleJPQLRepository.selectSingleMenuTypedQuery();
        assertEquals("한우딸기국밥", menuName);
    }



    @DisplayName("Query를 이용한 단일행, 단일 컬럼 조회")
    @Test
    void testSelectSingleMenuByQuery(){
        Object menuName = simpleJPQLRepository.selectSingleMenuQuery();
        assertEquals("한우딸기국밥", menuName);

    }


    @DisplayName("TypedQuery를 이용한 단일행 조회")
    @Test
    void testSelectSingleRowByQuery(){
        Menu menu = simpleJPQLRepository.selectSingleRowMenuQuery();
        assertEquals("한우딸기국밥", menu.getMenuName());

    }


    @DisplayName("TypedQuery를 이용한 다중행 조회")
    @Test
    void testSelectMultiRowByTypedQuery(){
        List<Menu> menuList = simpleJPQLRepository.selectMultiRowByTypedQuery();
        assertNotNull(menuList);

    }

    ////////////////////////////연산자를 사용한 조회 테스트
    @DisplayName("DISTINCT 연산자를 이용한 다중행 조회")
    @Test
    void testSelectUsingDistinct(){
        List<Integer> categoryCodeList = simpleJPQLRepository.selectUsingDistinct();
        System.out.println("categoryCodeList : " + categoryCodeList);
        assertNotNull(categoryCodeList);

    }


    @DisplayName("11, 12 카테고리 코드를 가진 메뉴 목록 조회 테스트")
    @Test
    void testSelectUsingIn(){
        List<Menu> categoryCodeInList = simpleJPQLRepository.selectUsingIn();
        System.out.println("categoryCodeInList : " + categoryCodeInList);
        assertNotNull(categoryCodeInList);
    }

    @DisplayName("'마늘'이라는 문자열이 메뉴명에 포함 되는 메뉴 목록 조회")
    @Test
    void testSelectUsingLike(){
        List<Menu> categoryCodeLikeList = simpleJPQLRepository.selectUsingLike();
        System.out.println("catogeryCodeLikeList : " + categoryCodeLikeList);
        assertNotNull(categoryCodeLikeList);
    }

}