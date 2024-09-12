package com.ohgiraffers.jpql.section06.join;

import jakarta.persistence.*;


@Entity(name="Section06Menu")
@Table(name="tbl_menu")
public class Menu {
    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;

    // 메뉴 여러개, 카테고리 1개 > 다중성 기입
    @ManyToOne
    @JoinColumn(name = "categoryCode") //FK값
    private Category category;
    private String orderableStatus;

    public Menu() {}


}
