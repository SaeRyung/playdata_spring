package com.ohgiraffers.section01.xmlmapper;

import java.util.List;

public interface ElementTestMapper {
    List<MenuDTO> selectResultMapTest();
    // ㄴ selectResultMapTest 메소드 명과 동일한 코드 -> xml 파일, id값 부여

    List<MenuAndCategoryDTO> selectResultMapAssociationTest();
    // ㄴ ElementTestMapper.xml 내 select Id = "selectResultMapAssociationTest"

    List<CategoryAndMenuDTO> selectResultMapCollectionTest();
}