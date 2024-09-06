package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.jdbc.SQL;

public class SelectBuilderProvider {

    // selectAllMenu 메소드 반환값 String
    public String selectAllMenu() {
        // 메소드 안에 SQL 객체 넣기
        return new SQL()
                //SQL 객체 내에 여러가지 메소드가 있다(SELECT, FROM, WHERE...)
                .SELECT("menu_code")
                .SELECT("menu_name")
                .SELECT("menu_price")
                .SELECT("category_code")
                .SELECT("orderable_status")
                .FROM("tbl_menu")
                .WHERE("orderable_status = 'Y'")
                .toString(); // 위의 메소드 그대로 문자열화
    }
    // ㄴ 위의 코드대로 SQL 구문 생성된다.


    public String searchMenuByNameOrCategory(SearchCriteria searchCriteria) {

        SQL sql = new SQL();

        // SELECT, FROM 변경되지 않으므로 미리 셋팅
        sql.SELECT("menu_code", "menu_name", "menu_price", "category_code", "orderable_status")
                .FROM("tbl_menu");

        // 두가지 조건이 나열되었을 경우
        // category: 조회할 열에 따라
        if("category".equals(searchCriteria.getCondition())) {
            sql.JOIN("tbl_category USING (category_code)")
                    .WHERE("orderable_status = 'Y'")
                    .AND()
                    .WHERE("category_name = #{ value }");
        } else if("name".equals(searchCriteria.getCondition())) {
            // where 메소드에 가변 인자로 전달 시 자동적으로 AND로 조건이 처리되므로 OR의 경우 별도 메소드 사용할 것
            sql.WHERE("orderable_status = 'Y'", "menu_name LIKE CONCAT('%', #{ value }, '%')");
        }

        return sql.toString();
    }
}