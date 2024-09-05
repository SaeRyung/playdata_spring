package com.ohgiraffers.section01.xmlconfig;

import java.util.List;

public class PrintResult {

    public void printMenuList(List<MenuDTO> menuList) {
        System.out.println("===== 메뉴 목록 ======");
        for(MenuDTO menuDTO : menuList) {
            System.out.println(menuDTO);
        }
    }



    public void printMenu(MenuDTO menu) {
        System.out.println("=== 조회 된 메뉴 ===");
        System.out.println(menu);
    }



    public void printErrorMessage(String errorCode) {
        System.out.println("===== Error =====");
        String errorMessage = "";
        switch (errorCode) {
            case "selectList":
                errorMessage = "메뉴 목록 조회에 실패했습니다."; break;
            case "selectOne":
                errorMessage = "메뉴 코드로 메뉴 조회에 실패"; break;
            case "insert":
                errorMessage = "신규 메뉴 등록에 실패"; break;
        }

        System.out.println(errorMessage);
    }



    public void printSuccessMessage(String successCode) {
        System.out.println("=== Success ===");
        String successMessage = "";
        switch (successCode){
            case "insert":
                successMessage = "신규 메뉴 등록 완료";
                break;
        }
        System.out.println(successMessage);
    }
}