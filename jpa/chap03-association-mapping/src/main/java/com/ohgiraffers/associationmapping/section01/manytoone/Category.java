package com.ohgiraffers.associationmapping.section01.manytoone;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="section01Category")
@Table(name="tbl_category")
public class Category {

    // @Id 필수 기입해야 엔티티로 인식된다.
    @Id
    //컬럼 - 필드 규칙만 잘 맞춰준다면 @Column 생략 가능
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
}
