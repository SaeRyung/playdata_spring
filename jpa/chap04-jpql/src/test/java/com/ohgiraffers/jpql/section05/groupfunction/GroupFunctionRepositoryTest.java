package com.ohgiraffers.jpql.section05.groupfunction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupFunctionRepositoryTest {

    @Autowired
    GroupFunctionRepository groupFunctionRepository;

    @DisplayName("특정 카테고리에 해당하는 메뉴 수 조회")
    @Test
    void testCountMenuOfCategory(){
        int categoryCode = 777; //의도적으로 없는 값 777 넣기
        long countOfMenu = groupFunctionRepository.countMenuOfCategory(categoryCode);
        assertTrue(countOfMenu >= 0);
    }


    @DisplayName("특정 카테고리에 해당하는 메뉴 가격 합 조회")
    @Test
    void testSumMenuPriceofCategory(){
        int categoryCode = 777; //의도적으로 없는 값 777 넣기
//        long sumMenuPrice = groupFunctionRepository.SumMenuPriceOfCategory(categoryCode);
//        assertTrue(sumMenuPrice >= 0);

        assertDoesNotThrow(
                ()->{
            Long sumMenuPrice = groupFunctionRepository.SumMenuPriceOfCategory(categoryCode);
            System.out.println("sumMenuPrice = " + sumMenuPrice);
        }
        );
    }


    @DisplayName("Having절 조회 테스트")
    @Test
    void testSelectByGroupByHaving(){
        long minPrice = 50000L;
        List<Object[]> sumPriceOfCategoryList = groupFunctionRepository.selectByGroupByHaving(minPrice);
        assertNotNull(sumPriceOfCategoryList);
        sumPriceOfCategoryList.forEach(
                row -> { // 한 행당
                    for(Object column : row){ // 어떤 행이 있는지 출력
                        System.out.print(column + " ");
                    }
                    System.out.println(); //다음 행 넘어갈때 개행
                }
        );
    }

}