package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* DispatcherServlet 은 웹 요청을 받는 즉시 @Controller가 달린 컨트롤러 클래스에 처리를 위임한다.
 * 그 과정은 컨트롤러 클래스의 핸들러 메서드에 선언 된 다양한 @RequestMapping 설정 내용에 따른다.
 * */

// Handler(Controller)
//@Controller: 클래스 Bean 등록할 수 있도록, 이 클래스는 Controller 역할
@Controller
public class MethodMappingTestController {
    // @RequestMapping: "/menu/regist" - menuRegist 연결
    // @Controller 하위 작성 : 핸들러 메서드

    /* 1. 요청 메소드 미지정 (메소드 방식과 무관하게 매핑 된다) -> GET, POST 요청 O */
    // @RequestMapping : "/menu/regist" 주소값에 연결하는 기능
    @RequestMapping("/menu/regist")
    public String menuRegist(Model model) {
        // Model > springframework import

        /* Model 객체에 addAttribute 메서드를 이용해 key, value를 추가하면
         * view에서 사용할 수 있다. -> chap03-view-resolver에서 다시 다룸 */
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출");
        // key: message , value: 신규 메뉴 등록용...


        /* 반환하고자 하는 view의 경로를 포함한 이름을 작성한다.
         * resources/templates 하위부터의 경로를 작성한다. -> chap03-view-resolver에서 다시 다룸 */
        return "mappingResult";
        // 응답할 화면을 포함한 경로 -> resource

    }

    /* 2. 요청 메소드 지정 */
    // Get 방식으로만 연결
    @RequestMapping(value= "/menu/modify", method = RequestMethod.GET)
    public String menuModify(Model model) {

        model.addAttribute("message", "GET 방식의 메뉴 수정용 핸들러 메소드 호출");

        return "mappingResult";
    }


    // 좀 더 간결하게 작성
    /* 3. 요청 메소드 전용 어노테이션 */
    @GetMapping("/menu/delete")
    public String getMenuDelete(Model model) {
        model.addAttribute("message", "GET 방식의 메뉴 삭제용 핸들러 메소드 호출");

        return "mappingResult";
    }


    @PostMapping("/menu/delete")
    public String postMenuDelete(Model model) {
        model.addAttribute("message", "POST 방식의 메뉴 삭제용 핸들러 메소드 호출");

        return "mappingResult";
    }




}