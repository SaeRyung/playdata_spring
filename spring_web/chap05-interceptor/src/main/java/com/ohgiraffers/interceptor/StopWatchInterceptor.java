package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/* HandlerInterceptor 인터페이스를 구현한 클래스로 작성 */
// interceptor에서 동작할 클래스 구현
@Component
public class StopWatchInterceptor implements HandlerInterceptor {


    /* Interceptor 스프링 컨테이너에 존재하는 빈을 의존성 주입 받을 수 있다.*/
    private final MenuService menuService;

    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    //HandlerInterceptor:  몸체(default) 존재하고, 굳이 전처리 후처리 다 할 필요 없다. 내가 필요한 시점만 골라서 오버라이딩 하면 된다.
    // 테스트 하기 위해 HandlerInterceptor 가 가지고 있는 메소드 전부 오버라이드


    /* 전처리 메소드 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle 메소드 호출");

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        return true; // 컨트롤러 핸들러 메소드의 호출로 이어짐, false이면 호출하지 않음
    }




    /* 후처리 메소드 */
    // Controller 반환 > modelAndView 값 반환
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 메소드 호출함");

        long endTime = System.currentTimeMillis();
        long startTime = (Long) request.getAttribute("startTime");

        modelAndView.addObject("interval", endTime - startTime);
    }
    // 뷰가 랜더링 된 이후 동작 > postHandle 적합



    /* 뷰가 랜더링 된 이후 동작하는 메소드*/
    // 랜더링 이후 동작하는 메소드, postHandle 보다 더 나중에 동작
    // 후처리 때 Exception 발생할 수 있으므로,,,exception 발생하더라도 동작하기에 적합한 메소드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 메소드 호출");
        menuService.method(); //MemuService도 의존성 주입으로 기능 수행 가능하다.
    }
}
