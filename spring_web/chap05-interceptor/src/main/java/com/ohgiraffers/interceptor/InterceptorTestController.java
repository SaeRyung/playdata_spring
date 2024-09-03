package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class InterceptorTestController {
    // 요청을 받아줄 controller handler 메소드
    @GetMapping("/stopwatch")
    public String stopwatch() throws InterruptedException {
        System.out.println("핸들러 메소드 호출함");
        Thread.sleep(100);
        return "result";
    }
}

/* * Dispatch servlet 실행 > Interecptor 에서 가로챌 때 스탑워치 키고 > Controller-stopwath 에 도달
*  > 다시 돌아갈 때 interceptor에 도달할 때 스탑워치 끄고 > dispatch servlet 도착,,,
* 전처리, 후처리에 스탑워치 키고 켜서 얼마나 걸리는지 체킹* */