package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

//import static sun.management.MemoryNotifInfoCompositeData.getCount;


public class EntityManagerCRUD {

    // EntityManager 통해 crud
    // EntityManager를 사용하여 DB와의 상호작용 기능
    private EntityManager entityManager;

    /* 1. 특정 메뉴 코드로 메뉴를 조회하는 기능 */
    public Menu findMenuByMenuCode(int menuCode){
        entityManager = EntityManagerGenerator.getInstance();
        // EntityManager 인스턴스를 생성 (각 기능에서 별도로 EntityManager 요청)
        // 매 기능마다 EntityManager 필요하므로 기능 수행때마다 요청해서 반환
        return entityManager.find(Menu.class, menuCode);
        // ㄴ 반환타입, find할때 어떤 값을 pk로 사용할지 = 특정 엔티티를 기본 키를 기준으로 조회한다.
        // find : 특정 엔티티 조회


        // goto -> Test
    }



    /* 2. 새로운 메뉴 저장하는 기능 */
    // 저장 후 카운트 하기 위한 클래스
    public Long saveAndReturnAllCount(Menu newMenu){
        entityManager = EntityManagerGenerator.getInstance();
        // 요청마다 entityManager는 새로운 객체 생성

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin(); // 트랜잭션 시작

        entityManager.persist(newMenu);
        // persist : 저장 > 엔터티 객체 전달해야 한다 newMenu

        entityTransaction.commit(); // 트랜잭션 커밋

        return getCount(entityManager);
    }


    /* 메뉴 개수 조회 기능 */
    // persist 후 return 값, getCount 분리된 메소드로 작성하나 엔티티 공유
    private Long getCount(EntityManager entityManager) {
        return entityManager // JPQL 문법(Entity 다룸) -> 별도 챕터에서 다룬다. SQL 과 다르다.
                .createQuery("SELECT COUNT(*) FROM Section02Menu", Long.class)
                // 엔터티 수행하는 문법, Section02Menu : 엔터티 테이블
                .getSingleResult();
        // 하나의 값 반환받겠다.
    }


    /* 3. 메뉴 이름 수정 기능 */
    public Menu modifyMenuName(int menuCode, String menuName){
        entityManager = EntityManagerGenerator.getInstance();
        // 실행할때마다 entityManager 가져와야 한다.


        // 3-2-1. 실제 데이터 연동 > 트랜잭선 관리(실제 데이터 수정 후 커밋)
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // 3-1. 객체 찾아오기
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        // 객체 수정, setMenuName 메소드 만들기
        foundMenu.setMenuName(menuName);

        // 3-2-2. 커밋 후 반환
        entityTransaction.commit();
        return foundMenu;

    }



    /* 4. 특정 메뉴 코드로 메뉴 삭제하는 기능 */
    public long removeAndReturnAllCount(int menuCode){
        entityManager = EntityManagerGenerator.getInstance();

        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        // 메뉴 삭제할 엔티티 찾아오기

        // 엔티티매니저 생성
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.remove(foundMenu); //삭제할 엔티티 전달

        entityTransaction.commit();

        return getCount(entityManager); // 전체 개수 요청
    }
}
