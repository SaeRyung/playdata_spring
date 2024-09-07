package com.ohgiraffers.springmybatis.section01.factorybean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Application 에서 스캔한 빈을 활용하여 테스트 실행한다.

@SpringBootTest
// ㄴ spring ioc 사용하는 bean 객체 통한 테스트 하기 위한 기능
class MenuServiceTest {

    @Autowired
    // 테스트 원하는 대상 의존성 주입
    private MenuService menuService;

    @DisplayName("주문 가능 상태별 메뉴 조회 확인 테스트") // 실행시 보여줄 이름
    @ParameterizedTest // 파라미터 넘어오도록,, (String orderableStatus) 으로 테스트 여러번
    @ValueSource(strings = {"Y", "N"}) // Y, N 값을 파라미터로 넘기기
    void testFindAllMenu(String orderableStatus){
        Assertions.assertDoesNotThrow( //throw 일어나지 않는다
                () -> {
                    // 테스트 할 코드
                    List<MenuDTO> menus = menuService.findAllMenuByOrderableStatus(orderableStatus);
                    menus.forEach(System.out::println);

                }
        );
    }
}