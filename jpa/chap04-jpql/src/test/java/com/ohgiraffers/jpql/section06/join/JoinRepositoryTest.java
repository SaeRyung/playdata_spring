package com.ohgiraffers.jpql.section06.join;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JoinRepositoryTest {

    @Autowired
    JoinRepository joinRepository;

    // 값이 같은 경우 조인 테스트
    @DisplayName("내부 조인 테스트")
    @Test
    void testSelectByInnerJoin(){
        List<Menu> menuList = joinRepository.selectByInnerJoin();
        assertNotNull(menuList);
    }


    // 메뉴 조회하는 순간 같이 조인 실행, 카테고리 값을 셀렉해서 매핑한다.
    @DisplayName("페치 조인 테스트")
    @Test
    void testSelectByFetchJoin(){
        List<Menu> menuList = joinRepository.selectByFetchJoin();
        assertNotNull(menuList);
    }


    // outerjoin
    @DisplayName("외부 조인 테스트")
    @Test
    void testSelectByOuterJoin(){
        List<Object[]> menuList = joinRepository.selectByOuterJoin();
        assertNotNull(menuList);
        menuList.forEach(
                row -> { // 한 행당
                    for(Object column : row){ // 어떤 행이 있는지 출력
                        System.out.print(column + " ");
                    }
                    System.out.println(); //다음 행 넘어갈때 개행
                }
        );
    }


    // 카테고리 - 컬렉션 기준 조인
    @DisplayName("컬렉션 조인 테스트")
    @Test
    void testSelectByCollectionJoin() {
        List<Object[]> menuList = joinRepository.selectByCollectionJoin();
        assertNotNull(menuList);
        menuList.forEach(
                row -> { // 한 행당
                    for (Object column : row) { // 어떤 행이 있는지 출력
                        System.out.print(column + " ");
                    }
                    System.out.println(); //다음 행 넘어갈때 개행
                }
        );
    }


    // 크로스 조인 결과
    @DisplayName("세타 조인 테스트")
    @Test
    void testSelectByThetaJoin() {
        List<Object[]> menuList = joinRepository.selectByThetaJoin();
        assertNotNull(menuList);
        menuList.forEach(
                row -> { // 한 행당
                    for (Object column : row) { // 어떤 행이 있는지 출력
                        System.out.print(column + " ");
                    }
                    System.out.println(); //다음 행 넘어갈때 개행
                }
        );
    }

}