package com.ohgiraffers.jpql.section04.paging;

// JPA에서의 페이징처리

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // offset : 몇 개를 띄어놓는다. limit : 갯수 제한
    public List<Menu> usingPagingAPI(int offset, int limit){
        String jpql = "SELECT m FROM Section04Menu m ORDER BY m.menuCode DESC";

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                .setFirstResult(offset) // 조회를 시작할 위치 (0부터) -> ex. offset=10 0~9는 뛰어넘고 10부터 조회
                .setMaxResults(limit) // 조회할 데이터의 개수
                .getResultList();

        return menuList;
    }
}
