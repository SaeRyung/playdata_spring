package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // 1.  root('/') 또는 '/main' 요청이 있을 때
    @RequestMapping(value = {"/", "/main"})
    public String main() { // 2. main 메소드를 호출하고
        return "main";
        // 3. view "main"을 리턴하겠다. (/templates/main.html)
        // templates 하위 폴더 내의 파일 = view 파일
    }
    // http://localhost:8080/main
}