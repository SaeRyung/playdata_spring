package com.ohgiraffers.associationmapping.section03.bidirection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 양방향 연관관계를 맺었으므로 동시에 확인하기 위한 클래스

@Service
public class BiDirectionService {

    private BiDirectionRepository biDirectionRepository;

    public BiDirectionService(BiDirectionRepository biDirectionRepository) {
        this.biDirectionRepository = biDirectionRepository;
    }

    // menuCode 기반으로 조회해서 Menu 반환
    public Menu findMenu(int menuCode){
        return biDirectionRepository.findMenu(menuCode);
    }


    // 지연로딩 관찰 위해 트랜잭션
    @Transactional
    // categoryCode 기반으로 조회해서 Category 반환
    public Category findCategory(int categoryCode){
//        return biDirectionRepository.findCategory(categoryCode);
        // 카테고리 조회

        /* 양방향 참조르르 구현하면 양방향 그래프 탐색이 가능하다. */
        Category category = biDirectionRepository.findCategory(categoryCode);
        System.out.println(category.getMenuList());
        System.out.println(category.getMenuList().get(0).getCategory());
        // 반환된 MenuList 에서 특정메뉴값 0을 가져오고, 메뉴 0의 카테고리 탐색한다
        return category;
    }
}
