package com.ohgiraffers.jpql.section03.projection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProjectionTest {

    @Autowired
    ProjectionService projectionService;

    @Autowired
    ProjectionRepository projectionRepository;

    @DisplayName("단일 엔터티 프로젝션")
    @Test
    void testSingleEntityProjection(){
        List<Menu> menus = projectionService.singleEntityProjection();
        assertNotNull(menus);
    }

    @DisplayName("임베디드 타입 프로젝션")
    @Test
    void testEmbeddedTypeProjection(){
        List<MenuInfo> menuInfos = projectionRepository.embeddedTypeProjection();
        assertNotNull(menuInfos);
    }


    @DisplayName("스칼라 타입 프로젝션")
    @Test
    void testScalarTypeProjection(){
        List<Object[]> categoryList = projectionRepository.scalarTypeProjection();
        assertNotNull(categoryList);
        categoryList.forEach(
                row -> { // 한 행당
                    for(Object column : row){ // 어떤 행이 있는지 출력
                        System.out.println(column + " ");
                    }
                    System.out.println(); //다음 행 넘어갈때 개행
                }
        );
    }



    @DisplayName("new 명령어 프로젝션")
    @Test
    void testNowCommandProjection(){
        List<CategoryInfo> categoryInfos = projectionRepository.newCommandProjection();
        assertNotNull(categoryInfos);
        categoryInfos.forEach(System.out::println);
    }




}
