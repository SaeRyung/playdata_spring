package com.ohgiraffers.section01.scope.subsection02.prototype;
// scope : bean이 어떤 범위에서 생성되는지 확인
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /* 슈퍼에 상품이 진열 되어 있다. */
        // 메소드의 이름이 빈의 이름이다.
        Product carpBread = applicationContext.getBean("carpBread", Product.class);
        Product milk = applicationContext.getBean("milk", Product.class);
        Product water = applicationContext.getBean("water", Product.class);


        /* 한 손님이 쇼핑 카트를 꺼내 상품을 카트에 담는다. */
        ShoppingCart cart = applicationContext.getBean("cart", ShoppingCart.class);
        cart.addItem(carpBread);
        cart.addItem(milk);

        System.out.println("cart에 담긴 상품 : " + cart.getItem());

        /* 다른 손님이 쇼핑 카트를 꺼내 상품을 카트에 담는다. */
        ShoppingCart cart2 = applicationContext.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        System.out.println("cart2에 담긴 상품 : " + cart2.getItem());

        /* Bean의 스코프를 prototype으로 변경하면 매번 다른 객체의 cart를 반환한다.
        * 따라서 손님 두 명이 각각 다른 쇼핑 카트를 이용해 상품을 담을 수 있다.
        * */

    }
}
