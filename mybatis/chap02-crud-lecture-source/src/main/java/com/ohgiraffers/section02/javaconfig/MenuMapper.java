package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.annotations.*;
import java.util.List;

// MenuDAO 역할 인터페이스

public interface MenuMapper {

// MenuDTO로 해석되기 위한 @Results 맵,, @Results 선언 값 재사용 가능,,id값으로
    // 구성요소를 서로 연결
    // id 기본적으로 false 이나 id처리가 필요한 것들은 true
    @Results(id="menuResultMap", value = {
            @Result(id = true, property = "menuCode", column = "menu_code"),
            @Result(property = "menuName", column = "menu_name"),
            @Result(property = "menuPrice", column = "menu_price"),
            @Result(property = "categoryCode", column = "category_code"),
            @Result(property = "orderableStatus", column = "orderable_status"),
    })

    // 수행할 메소드 > 어노테이션으로 알려준다.
    @Select("        SELECT\n" +
            "               menu_code\n" +
            "             , menu_name\n" +
            "             , menu_price\n" +
            "             , category_code\n" +
            "             , orderable_status\n" +
            "          FROM tbl_menu\n" +
            "         WHERE orderable_status = 'Y'\n" +
            "         ORDER BY menu_code")
    List<MenuDTO> selectAllMenu();
    //MyBatis에서 인터페이스 구현체 만듬 > 구현체에서 @@Select 동작 수행 >
    // 수행결과에 따른 맵핑을 @Results, @Results 결과를 List<MenuDTO>에 맵핑하여 출력한다.


    // 위에서 @Results 어노테이션을 이용해 선언 된 id 재사용
    @ResultMap("menuResultMap")
    @Select("        SELECT\n" +
            "                menu_code\n" +
            "              , menu_name\n" +
            "              , menu_price\n" +
            "              , category_code\n" +
            "              , orderable_status\n" +
            "         FROM tbl_menu\n" +
            "        WHERE orderable_status = 'Y'\n" +
            "          AND menu_code = #{ menuCode }")
    MenuDTO selectMenuByMenuCode(int menuCode);



    @Insert("        INSERT INTO\n" +
            "            tbl_menu(menu_name, menu_price, category_code, orderable_status)\n" +
            "        VALUES\n" +
            "            (#{ menuName }, #{ menuPrice }, #{ categoryCode }, 'Y')")
    int insertMenu(MenuDTO menu);



    @Update("        UPDATE tbl_menu\n" +
            "           SET menu_name = #{ menuName },\n" +
            "               menu_price = #{ menuPrice },\n" +
            "               category_code = #{ categoryCode }\n" +
            "         WHERE menu_code = #{ menuCode }")
    int updateMenu(MenuDTO menu);



    @Delete("        DELETE FROM tbl_menu\n" +
            "        WHERE menu_code = #{ menuCode }")
    int deleteMenu(int menuCode);
}