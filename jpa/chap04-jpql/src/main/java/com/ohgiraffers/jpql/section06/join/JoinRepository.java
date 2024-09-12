package com.ohgiraffers.jpql.section06.join;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JoinRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // 동일값이 있었을 경우에만 > innerjoin
    public List<Menu> selectByInnerJoin(){
        String jpql = "SELECT m FROM Section06Menu m JOIN m.category";
        return entityManager.createQuery(jpql, Menu.class).getResultList();
        // 의도는 select 하면서 카테고리 값까지 채우기
        // but 카테고리 연관관계 매핑 값을 가져올때 지연로딩 발생, 필요할 때 카테고리 한번 더 조회
        // 비효율적
    }


    // 카테고리 필요한지 체킹을 추가
    public List<Menu> selectByFetchJoin(){
        /* FETCH : JPQL에서 성능 최적화를 위해 사용하는 기능으로
        * 연관 된 엔티티나 컬렉션을 한 번에 조회한다. (지연 로딩이 아닌 즉시 로딩을 수행한다.)*/
        String jpql = "SELECT m FROM Section06Menu m JOIN FETCH m.category";
        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }


    // 값은 다르나 기준으로 삼는 테이블의 행을 모두 조회할 수 있는 outerjoin
    public List<Object[]> selectByOuterJoin(){
        /* OuterJoin */
        String jpql = "SELECT m.menuName, c.categoryName FROM Section06Menu m RIGHT JOIN m.category c";
        // m.menuName, c.categoryName를 조회, 기준은 m.category c
        return entityManager.createQuery(jpql).getResultList();
    }

    //카테고리 기준 조인
    public List<Object[]> selectByCollectionJoin(){
        String jpql = "SELECT c.categoryName, m.menuName FROM Section06Category c LEFT JOIN c.menuList m";
        //Section06Category 의 List<Menu> menuList 가 기준이 된다.
        return entityManager.createQuery(jpql).getResultList();
    }

    //세타 조인(크로스 조인)
    public List<Object[]> selectByThetaJoin(){
        String jpql = "SELECT c.categoryName, m.menuName FROM Section06Category c, Section06Menu m";
        //ㄴ 조건을 적지 않는다 => c X m 만큼의 결과 => 모든 행들이 서로 연결
        return entityManager.createQuery(jpql).getResultList();
    }
}
