package com.ohgiraffers.associationmapping.section01.manytoone;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuToOneService {

    private ManyToOneMenuRepository manyToOneMenuRepository;

    public MenuToOneService(ManyToOneMenuRepository manyToOneMenuRepository){
        this.manyToOneMenuRepository = manyToOneMenuRepository;
    }

    public Menu findMenu(int menuCode){
        return manyToOneMenuRepository.find(menuCode);
    }

    public String findCategoryNameByJqpl(int menuCode){
        return manyToOneMenuRepository.findCategoryName(menuCode);
    }


    // DB에 저장 위해 트랜잭션
    @Transactional
    public void registMenu(MenuDTO newMenu){
        // menu 엔티티로 변환 후 저장한다.
        // menuDTO 값이 넘어와 그 값을 menu엔티티로 해석,
        Menu menu = new Menu(
                newMenu.getMenuCode(),
                newMenu.getMenuName(),
                newMenu.getMenuPrice(),
                new Category( // 연관관계 category 값도 채운다.
                        newMenu.getCategory().getCategoryCode(),
                        newMenu.getCategory().getCategoryName(),
                        newMenu.getCategory().getRefCategoryCode()
                ),
                newMenu.getOrderableStatus()
        );

        manyToOneMenuRepository.regist(menu);
    }
}
