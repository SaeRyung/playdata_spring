package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Application {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/menudb";
    private static String USER = "swcamp";
    private static String PASSWORD = "swcamp";

    public static void main(String[] args) {

        /* DB 접속에 관한 설정 */
        // JdbcTransactionFactory: 수동 커밋, ManagedTransactionFactory: 자동 커밋
        // 접속에 관련된 환경을 먼저 만들어준다
        // DB접속 -> mybytis 통해서
        // 1. 환경을 만듬
        Environment environment = new Environment(
                "dev", //환경 정보 이름
                new JdbcTransactionFactory(), //트랜잭션 매니저 종류(JDBC or MANAGED)
                new PooledDataSource(
                        DRIVER, URL, USER, PASSWORD) // Connection Pool 사용 유무(Pooled or UnPooled)
                // PooledDataSource: Connection 객체를 많이 만든 후 Pool에 넣어 바로바로 사용한다.
        );

        // 2. 환경을 기반으로 설정 객체 만듬
        /* 생성한 환경 설정 정보로 MyBatis 설정 객체 생성 */
        // 패키지 ibatis
        Configuration configuration = new Configuration(environment);

        /* 설정 객체에 매퍼(Mapper) 등록*/
        configuration.addMapper(Mapper.class);

        // 3. 설정 객체를 기반으로 sql세션팩토리 빌더 이용해서 spl세션 생성
        // Builder > 주로 앞에 있는 내용을 만들어내기 위한 역할
        /* SqlSessionFactory: SqlSession 객체를 생성하기 위한 팩토리 역할의 인터페이스
        * SqlSessionFactoryBuilder : SqlSessionFactory 인터페이스 타입의 하위 구현체를 생성하기 위한 빌드 역할 */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        //4. 오픈세션요청
        /* openSession() : SqlSession 타입의 인터페이스를 반환하는 메소드로 boolean 타입으로 인자 전달
        * false : DNL 수행 후 auto commit 옵션을 false 로 지정 */
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        /* getMapper() : Configuration에 등록 된 매퍼를 동일 타입에 대해 반환*/
        Mapper mapper = sqlSession.getMapper(Mapper.class);

        /* Mapper 인터페이스에 작성 된 메소드 호출하여 select 쿼리 실행 후 반환값 출력 */
        java.util.Date date = mapper.selectDate();
        System.out.println("date = " + date);

        /* SqlSession 객체 반납 */
        sqlSession.close();
    }
}
