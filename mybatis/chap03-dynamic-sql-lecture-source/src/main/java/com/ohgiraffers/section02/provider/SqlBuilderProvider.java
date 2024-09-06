package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.jdbc.SQL;

public class SqlBuilderProvider {

    public String insertMenu(MenuDTO menuDTO) {
        // 동적쿼리는 딱히, 값을 넣고 문자열화
        return new SQL()
                .INSERT_INTO("tbl_menu")
//                .VALUES("menu_name", menuDTO.getMenuName()) 객체로 처리도 할 수 있다.
                .VALUES("menu_name", "#{ menuName }")
                .VALUES("menu_price", "#{ menuPrice }")
                .VALUES("category_code", "#{ categoryCode }")
                .VALUES("orderable_status", "#{ orderableStatus }")
                .toString();
    }

    // 입력데이터 기반 동적쿼리, 실제 넘어온 데이터를 @UpdateProvider 에서 제공받아서 활용
    public String updateMenu(MenuDTO menuDTO) {
        SQL sql = new SQL();
        sql.UPDATE("tbl_menu");

        if(menuDTO.getMenuName() != null & !menuDTO.getMenuName().isEmpty()) {
            sql.SET("menu_name = #{ menuName }");
        }

        if(menuDTO.getOrderableStatus() != null & !menuDTO.getOrderableStatus().isEmpty()) {
            sql.SET("orderable_status = #{ orderableStatus }");
        }

        if(menuDTO.getCategoryCode() > 0) {
            sql.SET("category_code = #{ categoryCode }");
        }

        if(menuDTO.getMenuPrice() > 0) {
            sql.SET("menu_price = #{ menuPrice }");
        }

        sql.WHERE("menu_code = #{ menuCode }");

        return sql.toString();
    }


    // 매개변수 부로 넘어오지 않음.
    public String deleteMenu() {
        return new SQL()
                .DELETE_FROM("tbl_menu")
                // #{ menuCode } = > @Param("menuCode") 같게 쓰기
                .WHERE("menu_code = #{ menuCode }")
                .toString();
    }

}