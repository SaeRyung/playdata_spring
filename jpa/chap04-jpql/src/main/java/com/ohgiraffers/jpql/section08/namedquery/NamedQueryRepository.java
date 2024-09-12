package com.ohgiraffers.jpql.section08.namedquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

// 이름을 붙인 쿼리를 만들고 싶다!

@Repository
public class NamedQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> selectByDynamicQuery(String searchName, int searchCode) {
        // 파라미터 넘어온 값 기준으로 jpql 쿼리 생성
        StringBuilder jpql = new StringBuilder("SELECT m FROM Section08Menu m "); //디폴트 구문
        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            jpql.append("WHERE ");
            jpql.append("m.menuName LIKE '%' || :menuName || '%'");
            jpql.append("AND ");
            jpql.append("m.categoryCode = :categoryCode");
        } else {
            if(searchName != null && !searchName.isEmpty()) {
                jpql.append("WHERE ");
                jpql.append("m.menuName LIKE '%' || :menuName || '%'");
            } else if(searchCode > 0) {
                jpql.append("WHERE ");
                jpql.append("m.categoryCode = :categoryCode");
            }
        }

        // 위 생성 쿼리문으로 값 넘어오면 set파라미터
        // 넘어온 값이 있을 경우 실행, 하나만 넘어올 경우 등
        TypedQuery<Menu> query = entityManager.createQuery(jpql.toString(), Menu.class);
        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            query.setParameter("menuName", searchName);
            query.setParameter("categoryCode", searchCode);
        } else {
            if(searchName != null && !searchName.isEmpty()) {
                query.setParameter("menuName", searchName);
            } else if(searchCode > 0) {
                query.setParameter("categoryCode", searchCode);
            }
        }
        List<Menu> menuList = query.getResultList();
        return menuList;
    }

    public List<Menu> selectByNamedQuery() {
        List<Menu> menuList = entityManager.createNamedQuery("Section08Menu.selectMenuList", Menu.class)
                .getResultList();
        return menuList;
    }

    public List<Menu> selectByNamedQueryWithXml(int menuCode) {
        List<Menu> menuList = entityManager.createNamedQuery("Section08Menu.selectMenuByCode", Menu.class)
                .setParameter("menuCode", menuCode)
                .getResultList();
        return menuList;
    }





}