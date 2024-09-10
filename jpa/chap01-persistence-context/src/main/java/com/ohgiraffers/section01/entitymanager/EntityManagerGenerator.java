package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerGenerator {
    // EntityManager : 이 동작은 필요할때마다 호출한다.
    // ㄴ 엔티티 저장하는 메모리상 DB, 엔티티 저장, 수정, 삭제, 조회 등 관련된 모든 일 실행한다.
    // 프로그램 실행 할 때마다 실행-종료
    public static EntityManager getInstance(){
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();
        // EntityManagerFactory : 엔티티 매니저 생성할 수 있는 기능 제공 클래스
        // 싱글톤으로 생성해서 관리한다. 따라서 DB 사용하는 애플리케이션 당 한개 EntityManagerFactory 생성
        return factory.createEntityManager();
    }
}
