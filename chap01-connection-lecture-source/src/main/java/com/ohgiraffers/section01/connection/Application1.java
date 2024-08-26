package com.ohgiraffers.section01.connection;
// 자바에서 사용하는 sql 패키지
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 해당 DBMS와 계정에 맞는 Connection 객체를 생성할 수 있다. */
public class Application1 {
    public static void main(String[] args) {

        Connection con = null;
        try {
            /* 문자열로 작성된 class 명칭이 잘 못 되었을 경우 ClassNotFoundExcepionn이 발생할 수 있으므로
            * Exception handling 한다. */
            // className이 문자열이기에 에러 날 수 있으므로
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection: 어떤 dbms 계정과 연동하는지 반환하는 역할
            /* DBMS 연결 정보가 잘못 된 경우 connection 객체 생성이 불가능하므로 SQLException 발생할 수 있다. */
            // !AuthenticationProvider.BadAuthenticationPlugin! : 계정명이 다른 경우 에러
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","prictice","prictice");

            // Connection con 잘 만들어졌는지 확인위한 출력
            System.out.println("con = " + con);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // 스트림처럼 누수로 인한 닫아주기 필요
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
