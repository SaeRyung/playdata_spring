package com.ohgiraffers.associationmapping.section02.onetomany;


import jakarta.persistence.*;

import java.util.List;

@Entity(name="section02Category")
@Table(name="tbl_category")
public class Category {

    // @Id 필수 기입해야 엔티티로 인식된다.
    @Id
    //컬럼 - 필드 규칙만 잘 맞춰준다면 @Column 생략 가능
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    /* @OneToMany 의 경우 default Fetch.Type.LAZY로 설정 되어 있다.
    * 따라서 즉시 로딩이 필요한 경우 별도로 설정해 주어야 한다.*/
    // LAZY > List<Menu> 호출시에 로딩된다.
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryCode")
    // ㄴ fk값 작성
    /*
    tbl_category : PK
    tbl_menu : fk
    * */
    private List<Menu> menuList;


    public Category(){}



}
