package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cart")
@IdClass(CartCompositeKey.class) //Id의 타입입니다 라고 지정
public class Cart {
    // @Id 붙여놓은 값은 따로 취급해야한다. > 복합키 클래스 : CartCompositeKey

    @Id
    @Column(name = "cart_owner")
    private int cartOwner; //카트주인 : 유저 정보

    @Id
    @Column(name = "added_book")
    private int addedBook;


    @Column(name="quantity")
    private int quantity;


    public Cart() {
    }

    public Cart(int cartOwner, int addedBook, int quantity) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
        this.quantity = quantity;
    }
}
