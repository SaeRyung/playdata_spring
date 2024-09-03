package com.ohgiraffers.exceptionhandler;

/* 사용자 정의 Exception 클래스 */
public class MemberRegistException extends Exception {

    // 메세지 다루기 위해 생성자 선언, 생성자를 통해서 전달
    public MemberRegistException(String message) {
        super(message);
    }
}