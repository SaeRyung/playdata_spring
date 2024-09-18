package com.ohgiraffers.associationmapping.section03.bidirection;


import jakarta.persistence.*;

import java.io.BufferedReader;
import java.util.List;

// 양방향 매핑 ( 단방향 매핑 두번)

@Entity(name="section03Category")
@Table(name="tbl_category")
public class Category {

    // @Id 필수 기입해야 엔티티로 인식된다.
    @Id
    //컬럼 - 필드 규칙만 잘 맞춰준다면 @Column 생략 가능
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
    /* 양방향 연관 관계를 맺을 경우 FK를 가진 엔티티를 진짜,
    * FK를 가지지 않은 엔티티를 가짜 연관 관계라고 본다.
    * 진짜 연관 관계는 똑같이 처리하지만 가짜 연관 관계는
    * mappedBy 속성에 진짜 연관 관계 필드명을 넣어서 설정한다. */
    @OneToMany(mappedBy = "category")
    // ㄴ 가짜 연관관계 시 mappedBy
    private List<Menu> menuList;


    public Category(){}

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }



    // 서로 호출하기에 스택 오버플로우 발생한다.

//    @Override
//    public String toString() {
//        return "Category{" +
//                "categoryCode=" + categoryCode +
//                ", categoryName='" + categoryName + '\'' +
//                ", refCategoryCode=" + refCategoryCode +
//                ", menuList=" + menuList +
//                '}';
//    }
}
