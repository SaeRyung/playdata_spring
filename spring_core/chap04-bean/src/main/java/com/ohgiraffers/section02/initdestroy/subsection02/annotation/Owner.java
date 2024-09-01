package com.ohgiraffers.section02.initdestroy.subsection02.annotation;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component : ComponentScan을 통해 빈을 등록
// Owner라는 타입의 빈 등록됌
@Component
public class Owner {

    @PostConstruct //특정 메서드가 초기 메서드, initMethod 와 같은 설정 annotation
    // @PostConstruct: 객체 생성 직후 이 메소드 호출
    public void openShop(){
        System.out.println("사장님이 가게 문을 열었습니다. 이제 쇼핑을 하실 수 있습니다.OOO");
    }

    @PreDestroy //특정 메서드가 폐기 메서드, destroyMethod와 같은 annotation
    // @PreDestroy: 소멸 전 메소드 호출
    public void closeShop(){
        System.out.println("사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다. XXX");
    }
}
