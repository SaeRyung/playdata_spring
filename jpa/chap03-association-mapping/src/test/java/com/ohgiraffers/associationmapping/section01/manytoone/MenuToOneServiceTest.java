package com.ohgiraffers.associationmapping.section01.manytoone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuToOneServiceTest {

    @Autowired
    private MenuToOneService menuToOneService;

    @DisplayName("N:1 연관관계 객체 그래프 탐색을 이용한 조회 테스트")
    @Test
    void manyToOneFindTest(){
        //given
        int menuCode = 9; //DB에 존재하는 코드로 조회
        //when
        Menu foundMenu = menuToOneService.findMenu(menuCode);
        // ㄴ 특정 menuCode 9 요청해서 조회 > Menu 엔티티로 반환
        //then
        Category category = foundMenu.getCategory();
        // Menu 엔티티에 연관관계 매핑을 했으므로
        // Menu 엔티티로 카테고리 값을 접근
        assertNotNull(category);
    }



    @DisplayName("N:1 연관관계 객체 지향 쿼리(jpql)을 이용한 조회 테스트")
    @Test
    void manyToOneJpqlTest(){
        //given
        int menuCode = 9; //DB에 존재하는 코드로 조회
        //when
        String categoryName = menuToOneService.findCategoryNameByJqpl(menuCode);
        // ㄴ 특정 menuCode 9 요청해서 조회
        //then
        assertNotNull(categoryName);
        System.out.println("[Category Name] : " + categoryName);
    }


    private static Stream<Arguments> getMenuInfo(){
        return Stream.of(
                Arguments.of(123, "돈까스 스파게티", 30000, 123, "퓨전분식", "Y")
        );
    }

    @DisplayName("N:1 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getMenuInfo")
    void manyToOneInsertTest(
            int menuCode, String menuName, int menuPrice, int categoryCode, String categoryName, String orderableStatus
    ){
        // 저장요청
        MenuDTO menu = new MenuDTO(
                menuCode,
                menuName,
                menuPrice,
                new CategoryDTO(
                        categoryCode,
                        categoryName,
                        null
                ),
                orderableStatus
        );
        assertDoesNotThrow(
                ()-> menuToOneService.registMenu(menu)
        );
    }

}