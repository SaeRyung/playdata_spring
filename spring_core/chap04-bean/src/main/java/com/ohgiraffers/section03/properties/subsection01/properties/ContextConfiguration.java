package com.ohgiraffers.section03.properties.subsection01.properties;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
// resource 폴더 하위 경로를 기술하여 읽어올 properties 파일을 설정
@PropertySource("section03/properties/subsection01/properties/product-info.properties")
public class ContextConfiguration {

    /* 필드에 읽어온 값 주입 */
    // ${key: 붕어빵} 해당 key에 value값이 치환된다. :붕어빵=> default값, key값 못찾을경우
    // 치환자 문법 사용 시 공백 X
    /* 치환자 문법(placeholder)을 이용하여 properties에 저장 된 키 값을 입력하면 value에 해당하는 값이 가져와서 주입된다.
    * 양 옆에 공백이 있을 경우 값을 읽어오지 못하므로 주의한다.
    * 뒤에 :을 사용하여 해당 key 값이 없을 경우의 대체 값을 작성할 수 있다.*/
    @Value("${bread.carpbread.name:붕어빵}")
    private String name;

//    @Value("${bread.carpbread.naaaa:붕어빵}")
//    private String name;

    @Value("${bread.carpbread.price:0}")
    private int price;

    @Bean
    public Product carpBread(){
        return new Bread(name, price, new java.util.Date());
    }

    // 매개변수에 주입 가능
    /* 매개변수에 읽어온 값 주입 */
    @Bean
    public Product milk(@Value("${beverage.milk.name}")String name,
                        @Value("${beverage.milk.price:0}")int price,
                        @Value("${beverage.milk.capacity:0}")int capacity){
        return new Beverage(name, price, capacity);
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
}
