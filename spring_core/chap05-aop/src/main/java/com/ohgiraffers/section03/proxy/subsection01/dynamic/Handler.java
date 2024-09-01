package com.ohgiraffers.section03.proxy.subsection01.dynamic;

import com.ohgiraffers.section03.proxy.common.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// proxy 객체 만들기
// 1. reflect
public class Handler implements InvocationHandler {

    /* 타겟 오브젝트 - interface 타입만 가능: Student*/
    private final Student strudent;
    public Handler(Student strudent) {
        this.strudent = strudent;
    }

    @Override
    // proxy객체 생성 후 감싸고 동작할 곳
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("=== 공부 너무 하고 싶어요 ===");
        System.out.println("호출 대상 메소드 : " + method);
        //전
        for(Object arg : args){
            System.out.println("전달 인자 : " + arg);
        }

        method.invoke(strudent, args); //타겟메소드 호출

        System.out.println("==== 공부 마치고 수면 학습 zzzZZ ====");

        //후
        return proxy;
    }
}
