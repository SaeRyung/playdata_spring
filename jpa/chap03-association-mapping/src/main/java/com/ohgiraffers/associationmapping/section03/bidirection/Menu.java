package com.ohgiraffers.associationmapping.section03.bidirection;


import jakarta.persistence.*;

// 양방향 매핑 ( 단방향 매핑 두번)

@Entity(name = "section03Menu")
@Table(name = "tbl_menu")
public class Menu {

    // @Id 필수 기입해야 엔티티로 인식된다.
    @Id
    //컬럼 - 필드 규칙만 잘 맞춰준다면 @Column 생략 가능
    private int menuCode;
    private String menuName;
    private int menuPrice;
    @ManyToOne
    @JoinColumn(name = "categoryCode")
    // ㄴ FK를 가지고 있는 진짜 연관관계이므로 JoinColumn 작성
    private Category category;
    private String orderableStatus;

    public Menu() {}

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }


    // 서로 호출하기에 스택 오버플로우 발생한다.

//    @Override
//    public String toString() {
//        return "Menu{" +
//                "menuCode=" + menuCode +
//                ", menuName='" + menuName + '\'' +
//                ", menuPrice=" + menuPrice +
//                ", category=" + category +
//                ", orderableStatus='" + orderableStatus + '\'' +
//                '}';
//    }
}
