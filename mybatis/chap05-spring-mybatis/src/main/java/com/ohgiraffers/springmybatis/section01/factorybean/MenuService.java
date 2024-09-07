package com.ohgiraffers.springmybatis.section01.factorybean;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //1. spring ioc를 통해 구동 > 클래스 Bean 등록
public class MenuService {

    private final SqlSessionTemplate sqlSession;
    // SqlSessionTemplate 도 ioc 컨테이너에 등록한 bean 이므로 의존성주입 받아서 써야 한다.

    public MenuService(SqlSessionTemplate sqlSession){
        this.sqlSession = sqlSession;
    }

    // orderableStatus 가 Y? N 인지 기준으로 조회하는 기능
    // 2. 같은패키지 MenuDTO 작성
    public List<MenuDTO> findAllMenuByOrderableStatus(String orderableStatus){
        return sqlSession.getMapper(MenuMapper.class).findAllMenuByOrderableStatus(orderableStatus);
        // Mapper 내 함수 findAllMenuByOrderableStatus
    }

}
