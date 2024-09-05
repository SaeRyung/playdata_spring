package com.ohgiraffers.section01.xml;

// 경로와 파일명 동일하게 맞추기 - resource ,, DynamicSqlMapper.xml

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DynamicSqlMapper {
    // DynamicSqlMapper.xml 에 동일한 이름의 메소드로 작성하여 매핑 > xml 파일에서 id값
    List<MenuDTO> selectMenuByPrice(Map<String, Integer> map);

    List<MenuDTO> searchMenu(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuBySupCategory(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuByRandomMenuCode(Map<String, Set<Integer>> criteria);

    List<MenuDTO> searchMenuByNameOrCategory(Map<String, Object> criteria);

    int updateMenu(Map<String, Object> criteria);
}