package com.ohgiraffers.section02.initdestroy.subsection01.java;

public class Owner {
// Bean 생성 후 초기화 과정에서 initMethod -> 첫번째로 출력됌
    public void openShop(){
        System.out.println("사장님이 가게 문을 열었습니다. 이제 쇼핑을 하실 수 있습니다.OOO");
    }

    public void closeShop(){
        System.out.println("사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다. XXX");
    }
}
