package com.ohgiraffers.section03.entity;

import com.ohgiraffers.section01.entitymanager.EntityManagerGenerator;
import jakarta.persistence.EntityManager;

public class EntityLifeCycle {
    private EntityManager entityManager;

    public Menu findMenuByMenuCode(int menuCode){
        entityManager = EntityManagerGenerator.getInstance();
        // ㄴ 엔티티매니저 요청, 새로 생성된다
        return entityManager.find(Menu.class, menuCode);
        // ㄴ 새로 생성 후 find 요청
    }

    // 확인 위한 반환 메소드
    public EntityManager getManagerInstance(){
        return entityManager;
    }
    //goto > test
}
