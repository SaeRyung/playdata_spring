package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

// 영속성 컨텍스트 내에서 해당 타입이 Id로 처리되기 위해서는 직렬화처리 필수!!
// 복합키 클래스 따로 생성

import java.io.Serializable;

public class CartCompositeKey implements Serializable {

    private int cartOwner;
    private int addedBook;


    public CartCompositeKey() {}

    public CartCompositeKey(int cartOwner, int addedBook) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
    }
}
