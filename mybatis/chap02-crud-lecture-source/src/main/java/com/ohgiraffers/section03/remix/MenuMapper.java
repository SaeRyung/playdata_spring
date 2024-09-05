package com.ohgiraffers.section03.remix;

import java.util.List;

public interface MenuMapper {
    MenuDTO menu = new MenuDTO();

// 인터페이스 수행해야하는 부분 -> 추상메소드로 만들기
    // 실제 수행은 xml 파일에 들어가도록 하기 위해
    // 인터페이스 이름 MenuMapper.java = MenuMapper.xml 파일 경로와 이름 같도록 설정
// 인터페이스 메소드 = xml id 같게 작성
    // 파라미터로 보내기로 한 타입과 반환값으로 받기로 한 타입 일치해야한다.
    List<MenuDTO> selectAllMenu();


    MenuDTO selectMenuByMenuCode(int menuCode);


    int insertMenu(MenuDTO menu);


    int updateMenu(MenuDTO menu);


    int deleteMenu(int menuCode);
}