package com.ohgiraffers.section03.entity;

import com.ohgiraffers.section01.entitymanager.EntityManagerGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EntityLifeCycleTest {

    private EntityLifeCycle entityLifeCycle;

    @BeforeEach
    void init(){ this.entityLifeCycle = new EntityLifeCycle(); }

    @DisplayName("비영속 테스트") //비영속 = 영속성 컨텍스트에서 관리되지 않는다.
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testTransient(int menuCode){
        // when
        Menu foundMenu = entityLifeCycle.findMenuByMenuCode(menuCode);

        Menu newMenu = new Menu(
                foundMenu.getMenuCode(),
                foundMenu.getMenuName(),
                foundMenu.getMenuPrice(),
                foundMenu.getCategoryCode(),
                foundMenu.getOrderableStatus()
        );


        EntityManager entityManager = entityLifeCycle.getManagerInstance(); //

        //then
        assertTrue(entityManager.contains(foundMenu)); //foundMenu는 영속성 컨텍스트에서 관리 되는 영속 상태의 객체
        // => foundMenu 는 EntityManager를 통해서 찾아온 객체이기 때문에 EntityManager로 관리되는 영속 객체
        assertFalse(entityManager.contains(newMenu)); //newMenu는 영속성 컨텍스트에서 관리 되지 않는 비영속 상태의 객체
        // => newMenu는 객체를 생성만 했을 뿐 아직 EntityManager에서 관리하고 있지 않기 때문에 비영속 객체

        /* contains
         영속성에서 find 요청 > 1차캐시에서 찾기 > 없다면 db에서 조회 후 > map(key,value)으로 저장
         관리되고 있는지 물어보는 함수. */
    }


    // 엔티티 두가지 상황으로 테스트
    @DisplayName("다른 엔터티 매니저가 관리하는 엔터티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    void testManagedOtherEntityManager(int menuCode){
        //when
        Menu menu1 = entityLifeCycle.findMenuByMenuCode(menuCode);
        //ㄴ 1번 영속성 컨택 > 엔티티 생성 1차 캐시
        Menu menu2 = entityLifeCycle.findMenuByMenuCode(menuCode);
        // 별도 영속성 컨택 생성 > 1번 다시 조회 후 1차 캐시에 객체 만들기

        //엔티티 생성한다 > 영속성 컨택 생성 > 두개 다른 객체이다.

        //then
        assertNotEquals(menu1, menu2);

    }

    @DisplayName("같은 엔터티 매니저가 관리하는 엔터티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    void testManagedSameEntityManager(int menuCode){
        //when
        EntityManager entityManager = EntityManagerGenerator.getInstance(); // 엔티티 매니저 하나 가져오고
        // find 요청, 하나를 통해서 find 를 두번 요청했기에 1차캐쉬에 담긴 값이 두 번 요청된다.
        Menu menu1 = entityManager.find(Menu.class, menuCode);
        Menu menu2 = entityManager.find(Menu.class, menuCode);
        // find > 1차캐쉬에서 조회 > 없으면 DB에서 셀렉트 > 셀렉트 값을 Menu로 바꾼 후 저장

        //then
        // 동일 객체 반환
        assertEquals(menu1, menu2);

    }




    // 준영속성 테스트
    @DisplayName("준영속화 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11, 1000", "12, 10000"})
    void testDetachEntity(int menuCode, int menuPrice){
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        //when
        entityTransaction.begin();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        // ㄴ foundMenu => 이 시점은 영속 엔티티
        // find > 1차캐쉬에서 조회 > 없으면 DB에서 셀렉트 > 셀렉트 값을 Menu로 바꾼 후 저장

        // detach : 특정 엔터티만 준영속 상태(영속성 컨텍스트가 관리하던 객체를 관리하지 않는 상태)로 만든다.
        // 영속성 컨텍스트에서 꺼낸다. => 관리목록에서 빠진다.
        entityManager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice); // 가격 변경

        // flush: 영속성 컨텍스트의 상태를 DB로 내보낸다. 단 commit 하지 않은 상태이므로 rollback 가능하다.
        entityManager.flush(); // 저장되어 있는 내용(인메모리)을 실제 DB로 반영, commit X


        //then
        // detach 후 find 이기에 값 다르다
        assertNotEquals(menuPrice, entityManager.find(Menu.class, menuCode).getMenuPrice());
        entityTransaction.rollback();
    }



    @DisplayName("준영속화 detach 후 다시 영속화 테스트")
    @ParameterizedTest
    @CsvSource({"11, 1000", "12, 1000"})
    void testDetachAndMergeEntity(int menuCode, int menuPrice){
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        //when
        entityTransaction.begin();
        Menu foundMenu = entityManager.find(Menu.class, menuCode); //Select
        entityManager.detach(foundMenu); // 준영속화
        foundMenu.setMenuPrice(menuPrice); // 준영속화한 데이터 수정
        // merge : 파라미터로 넘어온 준영속 엔터티 객체의 식별자 값으로 1차 캐시에서 엔터티 객체를 조회한다. => 기존값 수정된다.
        //(없으면 DB에서 조회하여 1차 캐시에 저장한다.)
        // 조회한 영속 엔터티 객체에 준영속 상태의 엔터티 객체의 값을 병합한 뒤 영속 엔터티 객체를 반환한다.
        // 혹은 조회할 수 없는 데이터라면 새로 생성해서 병합한다.

        entityManager.merge(foundMenu); //관리목록에서 지워진(변경된) 객체 다시 병합 => 반영된다. / 조회 후 업데이트
        entityManager.flush(); // 저장되어 있는 내용(인메모리)을 실제 DB로 반영


        //then
        // 동일 객체 반환
        assertEquals(menuPrice, entityManager.find(Menu.class, menuCode).getMenuPrice());
        entityTransaction.rollback();
    }



    @DisplayName("detach 후 merge한 데이터 update 테스트")
    @ParameterizedTest
    @CsvSource({"11, 하양 민트초코죽", "12, 까만 탈기탕후루"})
    void testMergeUpdate(int menuCode, String menuName){
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode); // 특정 메뉴 코드 찾아오기
        entityManager.detach(foundMenu); // 특정 메뉴 코드 detach

        //when
        foundMenu.setMenuName(menuName); // 꺼내온 값 수정
        Menu refoundMenu = entityManager.find(Menu.class, menuCode);

        entityManager.merge(foundMenu); //덮어쓰기 => 변화된 상황 반영되기 때문에 find한 refoundMenu도 반영된다.

        //then
        assertEquals(menuName, refoundMenu.getMenuName()); //값 같다

    }


    // Detach 테스트
    @DisplayName("detach 후 merge한 데이터 save 테스트")
    @Test
    void testMergeSave(){
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Menu foundMenu = entityManager.find(Menu.class, 20); //테이블 존재 코드로 테스트하기
        // ㄴ20번 코드 조회
        entityManager.detach(foundMenu);
        // ㄴ20번 영속성에서 사라지게 하기

        //when
        entityTransaction.begin();
        foundMenu.setMenuName("치약맛 초코 아이스크림");
        foundMenu.setMenuCode(999);
        //ㄴ detach한 foundMenu 변경하기

        entityManager.merge(foundMenu);
        // 1. 1차캐시 존재 유무,,, 관리의 기준 무조건 key값!
        // 2. 없으면 DB에서 조회 후 병합
        // 3. DB에도 없으면 999 로 병합
        entityTransaction.commit();
        // commit : 실제 DB 반영

        assertEquals("치약맛 초코 아이스크림", entityManager.find(Menu.class, 999).getMenuName());
    }


    @DisplayName("준영속화 clear 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void testClearParsistenceContext(int menuCode){
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        //when
        // clear : 영속성 컨텍스트를 초기화 한다. -> 영속성 컨텍스트 내의 모든 엔터티는 준영속화 된다.
        entityManager.clear();

        //then
        Menu expectedMenu = entityManager.find(Menu.class, menuCode);
        // ㄴ clear 했기에 DB에서 다시 find 하기 때문에 새로운 객체 조회되어 생성된다.
        assertNotEquals(expectedMenu, foundMenu);
    }



    @DisplayName("준영속화 close 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void testCloseParsistenceContext(int menuCode){
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        //when
        // close : 영속성 컨텍스트 종료된다 => 영속성 컨텍스트 내의 모든 엔터티는 준영속화 된다.
         entityManager.close();

        //then
//        Menu expectedMenu = entityManager.find(Menu.class, menuCode);
//        // ㄴ close한 객체는 찾을 수 없다.
//        assertNotEquals(expectedMenu, foundMenu);

        // then
        // 예외가 발생합니다 라는 뜻
        assertThrows(
                IllegalStateException.class,
                () -> entityManager.find(Menu.class, menuCode)
        );
    }



    // 삭제 테스트
    @DisplayName("영속성 엔터티 삭제 remove 테스트")
    @ParameterizedTest
    @ValueSource(ints = {2})
    void testRemoveEntity(int menuCode){
        //given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        // 조회 시 2번의 값 조회되에 객체참조 < 영속성 컨텍스트 내

        //when
        entityTransaction.begin(); // 트랜잭션 발생하여
        // remove : 엔터티를 영속성 컨텍스트 및 데이터 베이스에서 삭제한다.
        // 단, 트랜잭션을 제어하지 않으면 데이터 베이스에 영구 반영 되지는 않는다.
        entityManager.remove(foundMenu);
        // 영속성 내 관리목록에 있는 2번 delete를 기억한다.
        entityManager.flush(); //delete를 DB에 내보내기 실행

        //then
        Menu refoundMenu = entityManager.find(Menu.class, menuCode);
        assertNull(refoundMenu);
        // remove 했기에 null 나온다.
        entityTransaction.rollback(); //최종반영 원하지 않는다.
    }


}