package com.ohgiraffers.section02.initdestroy.subsection01.java;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ContextConfiguration {

    @Bean
    public Product carpBread(){
        return new Bread("붕어빵", 1000, new java.util.Date());
    }

    @Bean
    public Product milk(){
        return new Beverage("딸기우유", 1500, 500);
    }

    @Bean
    public Product water(){
        return new Beverage("지리산암반수", 3000, 1000);
    }

    @Bean
    @Scope("prototype") //default 값인 singleton에서 prototype으로 변경
    public ShoppingCart cart(){
        return new ShoppingCart();
    }

    // Owner Bean으로 등록
    // IoC 만들고 난 후 초기화 작업할 게 있다면 설정
    // 이 객체가 소멸될 때 소멸할 것이 있다면 정의하고 소멸한다.
    @Bean(initMethod = "openShop", destroyMethod="closeShop")
    public Owner owner(){
        return new Owner();
    }
}
