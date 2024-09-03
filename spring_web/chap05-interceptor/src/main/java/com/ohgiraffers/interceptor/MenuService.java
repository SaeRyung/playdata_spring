package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Service;
// 뭔가 기능이 있는 Service 클래스 가정한다.
@Service
public class MenuService {
    public void method(){
        System.out.println("MenuService 클래스의 method 호출");
    }
}
