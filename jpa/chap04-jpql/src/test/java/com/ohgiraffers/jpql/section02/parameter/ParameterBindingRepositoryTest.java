package com.ohgiraffers.jpql.section02.parameter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParameterBindingRepositoryTest {

    @Autowired
    ParameterBindingRepository parameterBindingRepository;

    @DisplayName("이름 기준 파라미터 바인딩 테스트")
    @Test
    void testParameterBindingByName(){
        String menuName = "한우딸기국밥";
        List<Menu> menus = parameterBindingRepository.selectMenuByBindingName(menuName);
        assertEquals(menuName, menus.get(0).getMenuName());
    }

    @DisplayName("위치 기준 파라미터 바인딩 테스트")
    @Test
    void testParameterBindingByPosition(){
        String menuName = "한우딸기국밥";
        List<Menu> menus = parameterBindingRepository.selectMenuByBindingPoint(menuName);
        assertEquals(menuName, menus.get(0).getMenuName());
    }

}