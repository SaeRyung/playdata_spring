package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Member;
import java.util.Map;

@Controller //1. 빈등록
@RequestMapping("/first/*") //2. 클래스 레벨에서 공통 주소 적기
@SessionAttributes("id")
public class FirstController {
    // GET ,,,,,/first/regist

    /* 핸들러 메서드의 반환값을 void로 설정하면 요청 주소가 곧 view의 이름이 된다.
    * => /first/regist 뷰를 응답한다.
    * => resources/templates/first/regist.html 파일을 만들어서 작업한다.
    * */
    @GetMapping("/regist")
    public void regist() {}

    /* 핸들러 메서드에 파라미터로 특정 몇 가지 타입을 선언하게 되면 핸들러 메서드 호출 시 인자로 값을 전달해준다.*/
    // 직접 public String registMenu() 안에 Servlet res 파라미터 전달은 가능하나 권장하지 않는다.


    /* 1. WebRequest로 요청 파라미터 전달 받기
    * HttpServletRequest/Response, ServletRequest/ServletResponse로 핸들러 메소드 매개변수로 사용 가능하지만
    * WebRequest가 Servlet에 종속적이지 않으므로 Spring 기반의 프로젝트에서 더 자주 사용 된다.
    * */
    @PostMapping("/regist")
    // WebRequest request: 스프링제공
    public String registMenu(WebRequest request, Model model){
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
        // 넘어온 파라미터값 변수에 담기

        String message = name + " 을(를) 신규 메뉴 목록의 " + categoryCode + " 번 카테코리에 " + price + " 원으로 등록하였습니다.";
        model.addAttribute("message", message);
        return "first/messagePrinter";
        // request 객체 받은 후 꺼내기
    }


    // "first/modify" 로 Get방식 요청이 올 경우 "first/modify.html" (view) 로 처리, 화면응답코드
    @GetMapping("/modify")
    public void modify(){
        }


    /*2. @RequestParam으로 요청 파라미터 전달 받기
    * 요청 파라미터를 매핑하여 핸들러 메소드 호출 시 값을 넣어주는 어노테이션으로 매개변수 앞에 작성한다.
    * name 속성과 매개변수 명이 다른 경우 @RequestParam("name")으로 설정한다.
    * (어노테이션 생략도 가능하다. -> 생략하려면 변수 동일해야한다. name, price로!
    * String name, int price,,,)
    *
    * 전달하는 name 속성과 일치하는 것이 없는 경우 400 에러가 발생하는데 이는 required 속성의 기본 값이 true 이기 때문이다.
    * 해당 속성을 false로 설정하면 값이 존재하지 않을 경우 null 처리가 된다.
    *
    * 값을 입력하지 않고 넘기면 "" 빈 문자열이 요청으로 넘어오는데 이 때 parsing 관련 에러가 날 수 있다.
    * defaultValue 속성을 이용하면 기본 값 설정이 가능하다.
    * */
    @PostMapping("/modify")
    // request 객체 꺼낸 후 전달해주세요 요청

    // name, price 전달값을 파라미터로 요청... 변수값 일치해야한다.
    // required = false > 존재하지 않을 때 null 처리
    // defaultValue = "0" > 넘어가는 값은 기본적으로 문자열이다.
    public String modifyMenu(@RequestParam(value = "name", required = false) String modifyName,
                             @RequestParam(value = "price", defaultValue = "0") int modifyPrice,
                             Model model){
        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "원으로 변경했다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }



    /* 파라미터가 여러 개인 경우 Map 으로 한 번에 처리할 수 있다. Map의 key는 name 속성이 된다.*/
    @PostMapping("/modifyAll")
    public String modifyAllMenu(@RequestParam Map<String, String> parameters, Model model){

        String name = parameters.get("name");
        int price = Integer.parseInt(parameters.get("price"));

        String message = name + "메뉴 가격을 " + price + "원으로 변경하였습니다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }




    @GetMapping("/search")
    public void search(){}

    /* 3, @ModelAttribute를 이용하는 방법
    * DTO 같은 모델을 커멘드 객체로 전달 발는 어노테이션으로 Model에 담기는 작업도 자동으로 일어난다.
    * 단 @ModelAttribute("key")를 지정하지 않으면 타입이 앞글자를 소문자로하여 저장 된다.
    * 어노테이션 생략도 가능하다. 생략하면 MenuDTO 이름으로 Model에 담긴다. */
    @PostMapping("/search")
    public String searchMenu(@ModelAttribute("menuu") MenuDTO menu){
        return "first/searchResult";
    }




    @GetMapping("/login")
    public void login(){}

    /* 4. @SessionAttribute
    * HttpSession을 전달 받는 것도 가능하지만 Servlet에 종속적이므로 Spring에서 제공하는 기능을 사용하는 것을 권장
    * 클래스 레벨에 @SessionAttributes("모델에 담을 key값")와 같이 지정하면
    * Model에 해당 key가 추가 될 때 Session에도 자동 등록 된다.*/
    // session 영역에 담기는 값,, 만료시켜줘야 한다.
    @PostMapping("/login")
    public String loginTest(String id, Model model){
        model.addAttribute("id", id);
        return "first/loginResult";
    }




    /*@SessionAttributes로 등록 된 값은 SessionStatus (세션의 상태를 관리하는 객체)의 selComplete() 메소드를 호출하여
    * 사용을 만료 시킨다.(HttpSession을 통한 session.invalidate() 메소드는 동작하지 않는다.)*/
    @GetMapping("/logout")
    public String logout(SessionStatus status){
        // "null 님 환영합니다", null값으로
        status.setComplete();
        return "first/loginResult";
    }



    @GetMapping("/body")
    public void body(){}


    // 출력 확인 목적
    @PostMapping("/body")
    public void bodyTest(
            @RequestBody String body,
            @RequestHeader("content-type") String contentType,
            @CookieValue("JSEESIONID") String sessionID
    ){
        System.out.println("body: " + body);
        System.out.println("contentType: " + contentType);
        System.out.println("sessionID: " + sessionID);

    }
}
