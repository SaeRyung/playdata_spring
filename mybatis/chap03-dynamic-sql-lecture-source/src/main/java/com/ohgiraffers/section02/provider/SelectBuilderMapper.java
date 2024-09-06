package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SelectBuilderMapper {

    // @SelectProvider : 수행될 구문을 따로 만들어서 가져오는 기능
    @SelectProvider(type = SelectBuilderProvider.class, method = "selectAllMenu")
    // SelectBuilderProvider.class 내에서 만든 SQL 구문내에서 method = selectAllMenu를 가져온다.
    List<MenuDTO> selectAllMenu();

    // 동일 클래스에서 메소드 이름에 해당되는 구문을 가져온다.
    @SelectProvider(type = SelectBuilderProvider.class, method = "searchMenuByNameOrCategory")
    List<MenuDTO> searchMenuByNameOrCategory(SearchCriteria searchCriteria);
}