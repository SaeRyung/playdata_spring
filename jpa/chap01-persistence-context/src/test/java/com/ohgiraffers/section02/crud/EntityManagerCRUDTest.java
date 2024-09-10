package com.ohgiraffers.section02.crud;


import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityManagerCRUDTest {

    // 테스트 해당 객체 필드 선언
    private EntityManagerCRUD entityManagerCRUD;

    @BeforeEach
    // 테스트 실행 전에 한번씩 실행, 독립적인 실행이다.
    void init(){
        // 테스트 할 때마다 초기화
        this.entityManagerCRUD = new EntityManagerCRUD();
    }

    @DisplayName("메뉴 코드로 메뉴 조회")
    @ParameterizedTest //파라미터 넘기면서 테스트
    @CsvSource({"1,1", "2,2", "3,3"}) // CSV(Comma Separated Values)는 콤마를 기준으로 구분
    void testFindMethodByMenuCode(int menuCode, int expected){
        //when
        Menu foundMenu = entityManagerCRUD.findMenuByMenuCode(menuCode);

        //then
        assertEquals(expected, foundMenu.getMenuCode()); //기대값, 실제값
        // ㄴ 예상값과 실제 메뉴코드 일치하는지 확인
        System.out.println("foundMenu = " + foundMenu);
    }

    private static Stream<Arguments> newMenu(){
        return Stream.of(
                Arguments.of(
                        "신메뉴",
                        35000,
                        4,
                        "Y"
                )
        );
    }


    @DisplayName("새로운 메뉴 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu") // 전달 > 메소드 소스 방식으로 전달하므로 arguments 작성한다.
    void testRegist(String menuName, int menuPrice, int categoryCode, String orderableStatus){
        //when
        Menu menu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);
        // ㄴ 넘어온 값 menu 엔터티 생성
        Long count = entityManagerCRUD.saveAndReturnAllCount(menu);

        //then
        assertEquals(29, count);
        // 테이블 갯수 확인 후 갯수 다음 번호에 넣는다.
    }


    // 메뉴 이름 수정 테스트
    @DisplayName("메뉴 이름 수정 테스트")
    @ParameterizedTest
    @CsvSource("1, 변경 된 이름")
    void testModifyMenuName(int menuCode, String menuName){
        //when
        Menu modifedMenu = entityManagerCRUD.modifyMenuName(menuCode, menuName);
        //then
        assertEquals(menuName, modifedMenu.getMenuName());
    }


    // 메뉴 삭제 테스트
    @DisplayName("메뉴 삭제 테스트")
    @ParameterizedTest
    @ValueSource(ints ={1001}) // 메뉴 코드를 전달
    void testRemoveMenu(int menuCode){
        //when
        Long count = entityManagerCRUD.removeAndReturnAllCount(menuCode);
        //then
        assertEquals(30, count); //총 개수 일치확인하여 삭제 여부 확인
    }


}