package com.ohgiraffers.jpql.section06.join;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity(name="Section06Category")
@Table(name="tbl_category")
public class Category {

    @Id
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category") //진짜 연관관계를 서술
    private List<Menu> menuList;
    // 양방향 연관관계 위해 menuList 만들기
    // 양방향 연관관계를 위해 진짜(FK)-->Menu, 가짜 연관관계 구분
}
