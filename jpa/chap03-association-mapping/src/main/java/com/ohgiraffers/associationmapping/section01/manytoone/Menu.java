package com.ohgiraffers.associationmapping.section01.manytoone;


import jakarta.persistence.*;

@Entity(name = "section01Menu")
@Table(name = "tbl_menu")
public class Menu {

    // @Id 필수 기입해야 엔티티로 인식된다.
    @Id
    //컬럼 - 필드 규칙만 잘 맞춰준다면 @Column 생략 가능
    private int menuCode;
    private String menuName;
    private int menuPrice;

    /* @ManyToOne 영속성 전이 : 특정 엔티티를 영속화 할 때 연관된 엔티티도 함께 영속화 한다는 의미
    * PERSIST: 저장할때
    * FetchType.EAGER : 즉시 로딩,
    * FetchType.LAZY : 지연 로딩 -> 연간관계 필요하지 않거나 추후 필요 시 필요시점에 출력한다.
    * @ManyToOne 어노테이션의 기본 속성은 즉시 로딩 (한 번에 join 해서 처리)
    * */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    //다중성 명시
    // N쪽 엔티티에서 사용하여 1 쪽의 엔티티와의 관계를 정의
    @JoinColumn(name="categoryCode") //어떤 컬럼과 조인할 것인지 기입
    // ㄴ fk에 해당하는 값을 넣어야 한다.
    private Category category;
    private String orderableStatus;


    public Menu() {}

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    // DB 변경 위험성이 있으므로 Entity에 보통 getter, setter 메소드 선언 X
    // 현재는 테스트 확인으로 선언함
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
}
