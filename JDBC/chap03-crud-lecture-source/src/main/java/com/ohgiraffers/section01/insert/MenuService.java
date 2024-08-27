package com.ohgiraffers.section01.insert;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.close;

/* Service 계층은 Connection 객체 생성 및 소멸(close), 비즈니스 로직, 트랜잭션(commit or rollback) 처리*/

// 서비스 계층: 비즈니스 로직 작성하는 계층이다 = 트랜잭션 단위가 관리된다.
public class MenuService {
    public void registMenu(Menu menu) {
        Connection con = getConnection(); //1. 메뉴등록 위해 DB 연결 객체 필요.
        // ** 1. DB 연결 시작

        // ** 2. DB 수행해야 할 동작이 수행 된 다음
        // 2. insert 위해 MenuRepository 계층 분리 위해 선언.
        MenuRepository menuRepository = new MenuRepository();
        // 3. 메뉴 등록해주세요 기능 호출
        int result = menuRepository.insertMenu(con, menu);

        // ** 3. 그 동작 결과에 따른 트랜잭션 처리 일어난 후
        // 4. 3번 수행 여부
        if(result > 0){
            commit(con);
        }else{
            rollback(con);
        }
        // ** 4. 열렸던 플로우 닫힌다.
        close(con);
    }
}
