package com.ohgiraffers.common;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {
    // 템플릿화 작업
    public static Connection getConnection(){
        // Properties 파일 읽어오기
        Properties props = new Properties();
        Connection con = null;

        try {
            props.load(new FileReader("src/main/java/com/ohgiraffers/config/jdbc-config.properties"));
            // 꺼내올때 키 값 기준으로 꺼내온다.
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            // 현재 필요로 하는 driver 로딩할 수 있도록
            Class.forName(driver);
            // 연결요청


            con = DriverManager.getConnection(url, user, password);
//            con = DriverManager.getConnection(url, props); => props 파일에서 user, password 키 값을 읽어서 처리


            System.out.println("con = " + con);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /* connection을 닫는 개념은 별도로 분리하고 실제 닫는 시점은 추후 Service 계층에서 진행할 예정이다.*/
        return con;
    }



    // 닫기
    // 호출 위해 public 으로 변경
    // 안전하게 종료
    public static void close(Connection con){
        try {
            if( con != null && !con.isClosed()) con.close(); //좀 더 안전하게 다룰 수 있도록. 스트림 열려있는 상태에서만 처리 위해 exception 처리
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Statement도 닫아줘야 한다.
    public static void close(Statement stmt){
        try{
            if(stmt != null && stmt.isClosed()) stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset){
        try{
            if(rset != null && rset.isClosed()) rset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
