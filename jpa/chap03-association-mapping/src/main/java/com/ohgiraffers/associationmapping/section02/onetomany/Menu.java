package com.ohgiraffers.associationmapping.section02.onetomany;


import jakarta.persistence.*;

@Entity(name = "section02Menu")
@Table(name = "tbl_menu")
public class Menu {

    // @Id 필수 기입해야 엔티티로 인식된다.
    @Id
    //컬럼 - 필드 규칙만 잘 맞춰준다면 @Column 생략 가능
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;


    public Menu() {}


}
