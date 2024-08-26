package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* Properties 파일의 설정 정보를 읽어와 Connection 생성하기 */
public class Application2 {
    public static void main(String[] args) {
        // Properties 파일 읽어오기
        Properties props = new Properties();
        Connection con = null;

        try {
            props.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));
            // 꺼내올때 키 값 기준으로 꺼내온다.
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            // 현재 필요로 하는 driver 로딩할 수 있도록
            Class.forName(driver);
            // 연결요청
            con = DriverManager.getConnection(url, user, password);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
        }
    }
    // 닫아주기 > main 바깥에서, 분리된 메소드로 작성
    private static void close(Connection con){
        try {
            if( con != null && !con.isClosed()) con.close(); //좀 더 안전하게 다룰 수 있도록. 스트림 열려있는 상태에서만 처리 위해 exception 처리
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
