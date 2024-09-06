package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SqlBuilderMapper {
    // @InsertProvider : insert 구문을 생성해서 전달하고 싶다.
    @InsertProvider(type = SqlBuilderProvider.class, method = "insertMenu")
    int insertMenu(MenuDTO menuDTO);

    @UpdateProvider(type = SqlBuilderProvider.class, method = "updateMenu")
    int updateMenu(MenuDTO menuDTO);

    /* 기본 자료형 값을 전달하는 경우 @Param 어노테이션을 이용해야 한다.
     * 또한 전달 값이 2개 이상인 경우도 @Param 어노테이션을 이용한다.
     * 단, Provider 메소드의 매개변수 선언부는 없어야 한다. */
    @DeleteProvider(type = SqlBuilderProvider.class, method = "deleteMenu")
    int deleteMenu(@Param("menuCode") int menuCode);
}