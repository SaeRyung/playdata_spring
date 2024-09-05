package com.ohgiraffers.section01.xmlconfig;

import java.awt.*;
import java.util.List;
import java.util.Map;

// MenuController : 요청 받은 후 넘어온 값을 가공, 처리 후 결과 > 비즈니스 로직 service 연결 > service에서 수행 완료 후 결과를 응답하는 것까지의 기능
// ㄴ 사용자에게 요청을 받은 후 응답에 대한 처리를 하는 역할

public class MenuController {

    // Service 필드로 참조
    private final MenuService menuService;
    private final PrintResult printResult;

    // 생성자 초기화
    public MenuController() {
        this.menuService = new MenuService();
        this.printResult = new PrintResult();
    }


    // 메뉴 전체 조회 메소드
    public void selectAllMenu() {
        // 반환 결과 출력 > 모든 메뉴 조회는 여러개 해당하는 메뉴값을 응답받아야 하므로 List,, 기능에 따른 메소드 시그니처를 결정한다.
        List<MenuDTO> menuList = menuService.selectAllMenu();

        // MenuService 에서 받은 반환결과값
        // 결과 출력은 PrintResult 클래스에서..
        if(menuList != null && !menuList.isEmpty()) {
            printResult.printMenuList(menuList);
        } else {
            printResult.printErrorMessage("selectList");
        }

    }


    // 메뉴 선택 코드 조회 메소드
    public void selectMenuByMenuCode(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));
        // 넘어온 파라미터 값 menuCode 가공 후 그 값을 기반으로 비즈니스 로직(MenuService) 호출

        // 전달된 메뉴 코드 기반으로
        MenuDTO menu = menuService.selectMenuByMenuCode(menuCode);
        if(menu != null){
            printResult.printMenu(menu);
        }else{
            printResult.printErrorMessage("selectOne");
        }
    }



    // 메뉴 insert 메소드
    public void registMenu(Map<String, String> parameter) {
        // 넘겨줄 파라미터 여러개이므로 MenuDTO로 넘겨준다.
        MenuDTO menu = new MenuDTO();

        menu.setMenuName(parameter.get("menuName"));
        menu.setMenuCode(Integer.parseInt(parameter.get("menuPrice")));
        menu.setCategoryCode(Integer.parseInt(parameter.get("categoryCode")));

        if(menuService.registMenu(menu)){ //성공했을 경우
            printResult.printSuccessMessage("insert");
        }else{ //실패했을 경우
            printResult.printErrorMessage("insert");

        }
    }


    // 메뉴 update 메소드
    public void modifyMenu(Map<String, String> parameter) {
        MenuDTO menu = new MenuDTO();

        menu.setMenuCode(Integer.parseInt(parameter.get("menuCode")));
        menu.setMenuName(parameter.get("menuName"));
        menu.setMenuCode(Integer.parseInt(parameter.get("menuPrice")));
        menu.setCategoryCode(Integer.parseInt(parameter.get("categoryCode")));

        if(menuService.modifyMenu(menu)){ //성공했을 경우
            printResult.printSuccessMessage("update");
        }else{ //실패했을 경우
            printResult.printErrorMessage("update");

        }
    }



    // 메뉴 delete 메소드
    public void deleteMenu(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));

        if(menuService.deleteMenu(menuCode)){ //성공했을 경우
            printResult.printSuccessMessage("delete");
        }else{ //실패했을 경우
            printResult.printErrorMessage("delete");

        }
    }
}