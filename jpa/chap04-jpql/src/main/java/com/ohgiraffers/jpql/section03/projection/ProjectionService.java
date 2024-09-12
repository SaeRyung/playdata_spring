package com.ohgiraffers.jpql.section03.projection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//ProjectionRepository 안에 jpql 작성, jpql을 통해 조회한 엔티티 반환되도록 작성

@Service
public class ProjectionService {

    private ProjectionRepository projectionRepository;

    public ProjectionService(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    // 조회된 리스트 내에서 index 0번 이름을 수정
    // 이 값이 영속성 컨텍스트 내에서 관리되는 엔티티가 되었는지 확인
    @Transactional
    public List<Menu> singleEntityProjection(){
        List<Menu> menus = projectionRepository.singleEntityProjectrion();
        menus.get(0).setMenuName("세상에서 제일 맛있는 유니콘 고기");
        return menus;
    }
}
