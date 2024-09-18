package com.ohgiraffers.section02.crud;

import jakarta.persistence.*;

// Entity 생략하면 클래스 이름 그대로 따라간다.
@Entity(name = "Section02Menu") // 이름 생략 시 class명과 동일, 중복 entity 명칭 불가
// ㄴ 엔티티가 jpa 관리 객체 영속성을 가진다.
@Table(name="tbl_menu") // 특정 테이블과 매핑되야 한다, 매핑 될 테이블 설정
public class Menu {

    // 엔티티는 생성자, getter, setter 필수는 X , 안정성 이유

    @Id // 엔티티는 항상 필드 내 프라이머리 키를 설정해야 한다. @Id 어노테이션 필수!!
    @Column(name = "menu_code") // 이 필드들이 매핑될 컬럼 정보 전달
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement 사용할 경우에 사용
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_price")
    private int menuPrice;
    @Column(name = "category_code")
    private int categoryCode;
    @Column(name = "orderable_status")
    private String orderableStatus;


    // JPA 사용하기 위해 기본생성자 필요하다.
    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    // test 위해 생성한 생성자 > Stream<Arguments> newMenu
    public Menu(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    // test 위해 get 작성
    public int getMenuCode() {
        return menuCode;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }

    // 3. 이름값 수정 기능 위해, setMenuName method 생성
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    // 3-1. 수정된 이름값 가져오기 기능 위해
    public String getMenuName() {
        return menuName;
    }
}
