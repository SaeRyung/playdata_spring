package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/controller-null")
    public String nullPointerExceptionTest() {
        String str = null;
        System.out.println(str.charAt(0));  // 의도적으로 NullPointerException 발생 시키는 코드
        return "/";
    }




    @GetMapping("/controller-user")
    public String userExceptionTest() throws MemberRegistException {
        boolean check = true;
        if(check) throw new MemberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다.");
        // 생성자 MemberRegistException 에 원하는 메세지 출력하도록
        // throw 사용 위해 throws

        return "/";
    }




    // NullPointerException 발생 시 여기 메소드에서 처리
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e) {
        // 매개변수에 선언하면 해당 exception 객체를 메소드에 전달된다.
        // 아래 sout 은 log로 찍힌다.
        System.out.println("특정 Controller 범위의 Exception Handler 동작");
        System.out.println("message : " + e.getMessage());

        return "error/nullPointer"; // templates/error/nullPoint.html 로 이동, 해당 문서 메세지 출력된다.
    }




    @ExceptionHandler(MemberRegistException.class)
    public String memberRegistExceptionHandler(MemberRegistException e, Model model) {
        // 응답화면에 바로 다루고 싶어서 Model 넣기
        System.out.println("특정 Controller 범위의 Exception Handler 동작");
        model.addAttribute("exceptionn", e);

        return "error/memberRegist";

    }








}