package com.ohgiraffers.section03.template;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// scope 와 생명 주기에 맞게 template 설정

public class Template {

    /* SqlSessionFactory : 애플리케이션 실행하는 동안 존재해야하며 여러 차례 다시 빌드하지 않는 것이 좋은 형태이다.
    * => singleton (오로지 1개의 객체만 생성)하는 패턴을 이용하는 것이 가장 좋다.
    * */
    // 4. 상단에 미리 선언하여 싱글톤으로
    private static SqlSessionFactory sqlSessionFactory;

    /* 1. 한 번의 요청 당 1개의 SqlSession 객체가 필요하므로 필요 시 호출한 메소드 */
    // sqlsession 필요할 때마다 요청할 메소드
    public static SqlSession getSqlSession() {

        if (sqlSessionFactory == null) {
            try {
                String resource = "mybatis-config.xml"; // 2. 호출 파일 읽기
                InputStream inputStream = Resources.getResourceAsStream(resource);
                /* SqlSessionFactoryBuilder : SqlSessionFactory 를 생성한 뒤 유지할 필요가 없으므로
                * 메소드 스코프로 만들면 된다. */
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); //3.

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        /* SqlSession : Thread에 안전하지 않고 공유 되지 않아야 하므로 요청 시 마다 생성한다. */
        return sqlSessionFactory.openSession(false);

    }
}
