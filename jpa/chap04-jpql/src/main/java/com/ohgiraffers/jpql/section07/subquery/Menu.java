package com.ohgiraffers.jpql.section07.subquery;

import jakarta.persistence.*;


@Entity(name="Section07Menu")
@Table(name="tbl_menu")
public class Menu {
    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;
    //subquery 연관관계 제거

    public Menu() {}


}
