package com.ohgiraffers.associationmapping.section01.manytoone;


import org.springframework.stereotype.Service;

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
}
