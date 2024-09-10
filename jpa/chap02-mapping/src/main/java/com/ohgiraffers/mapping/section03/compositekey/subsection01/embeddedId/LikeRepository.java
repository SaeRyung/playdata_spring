package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


//1. 빈등록
@Repository
public class LikeRepository {
    // 2. 의존성 주입, 영속성
    @PersistenceContext
    private EntityManager entityManager;

    // 3. 저장하는 동작 > LikeBookService
    public void save(Like like) {
        entityManager.persist(like);
    }
}
