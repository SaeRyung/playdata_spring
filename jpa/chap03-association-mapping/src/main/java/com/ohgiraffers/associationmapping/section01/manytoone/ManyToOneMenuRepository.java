package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToOneMenuRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Menu find(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }


    // 엔티티는 별칭 필수!
    public String findCategoryName(int menuCode){
        String jpql = "SELECT c.categoryName FROM section01Menu m JOIN m.category c WHERE m.menuCode = :menuCode";


        return entityManager.createQuery(jpql, String.class).setParameter("menuCode", menuCode).getSingleResult();
    }
}
