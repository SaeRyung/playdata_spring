package com.ohgiraffers.jpql.section03.projection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

// Projection : SELECT 절에 조회할 대상을 지정

@Repository
public class ProjectionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //ProjectionRepository 안에 jpql 작성, jpql을 통해 조회한 엔티티 반환되도록 작성
    /* SELECT절에 엔티티 쓰는것은 엔티티 프로젝션 이며
    영속성 컨텍스트에서 관리되는 엔티티이다. -> 이름값 변경 완료 */
    // 엔티티
    public List<Menu> singleEntityProjectrion(){
        String jpql = "SELECT m FROM Section03Menu m";
        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }


    // 타입 임베디드
    // 영속성 컨텍스트 관리 객체가 아닌 일반 클래스 타입으로 반환이 된다.
    // ProjectionTest에서 테스트
    public List<MenuInfo> embeddedTypeProjection(){
        String jpql = "SELECT m.menuInfo FROM EmbeddedMenu m";
        // FROM절 MenuInfo 사용 X => 엔티티가 아닌 임베디드 타입이기 때문이다.
        return entityManager.createQuery(jpql, MenuInfo.class).getResultList();
    }


    // 스칼라 타입
    // 영속성 컨텍스트 관리 객체가 아닌 일반 클래스 타입으로 반환이 된다.
    // 각각 단일값을 조회하겠다. (단일값=스칼라)
    // 두개이기에 매핑할 타입이 없으므로 생략 > Object로 반환된다. 두개이므로 배열로 처리
    public List<Object[]> scalarTypeProjection(){
        String jpql = "SELECT c.categoryCode, c.categoryName FROM Section03Category c";
        return entityManager.createQuery(jpql).getResultList();
    }


    // CategoryInfo로 취급해보기
    // 일반 클래스 타입 매핑 위해 CategoryInfo 생성자 호출
    public List<CategoryInfo> newCommandProjection(){
        String jpql = "SELECT new com.ohgiraffers.jpql.section03.projection.CategoryInfo(c.categoryCode, c.categoryName)" +
                "FROM Section03Category c";
        return entityManager.createQuery(jpql, CategoryInfo.class).getResultList();
    }


}
