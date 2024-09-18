package com.ohgiraffers.springdatajpa.menu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_menu")
@Getter //lombok > 꺼내온다.
/* @Setter 지양 : 객체를 언제든지 변경할 수 있는 상태가 되므로 객체의 안정성이 보장 받기 힘들다.
* 값 변경이 필요한 경우? 해당 기능을 수행하는 명확한 목적의 메소드를 정의하여 사용한다.*/
// 엔티티 연결된 파일은 DB와 연결되어 있다. 영속성 변경 위험 있으므로 새터 지양
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//ㄴ 기본생성자 접근 제한,, new Menu 아무곳에서 생성할 수 없도록 하는 기능
/* 기본 생성자 접근 제한을 통해 무분별한 객체 생성 지양
* @AllArgsConstructor 지양 : 인스턴스 선언 순서에 영향을 받으므로 변수 순서 변경 시 생성자 입력 값 순서도 바뀌어
* 검출하기 힘든 오류 발생 가능성이 있다.*/
/* @ToString : 사용 시 연관 관계 매핑 필드는 제거한다.,, 필수기능 X, 양방향시 서로참조하면 스택오버플로*/
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //ㄴ menuCode를 auto increment 위한 어노테이션
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

    public void modifyMenuName(String menuName) {
        this.menuName = menuName;
    }
}
