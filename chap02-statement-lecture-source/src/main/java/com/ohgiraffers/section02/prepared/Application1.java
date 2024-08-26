package com.ohgiraffers.section02.prepared;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            // PreparedStatement : 쿼리를 운반하고 결과를 반환하는 객체
            // preparedStatement(sql) : 객체 생성 시 수행할 sql 구문이 미리 전달됌
            pstmt = con.prepareStatement("SELECT * FROM employee");

            // (2) 연결 후 구문 생성 createStatement

            /* ResultSet: Statement 객체를 통해 조회 처리 된 결과를 다루는 객체 */
            //(3) 실행 : 조회 -> 자바 내에서 ResultSet 타입 반환을 받아 조회
            rset = pstmt.executeQuery();

            /* 결과 행이 존재하는지 확인 */
            while (rset.next()){

                /* rset 은 1행을 참조하고 있으므로 해당 행에서 필요한 컬럼을 getXXX 메소드로 타입을 맞춰서 꺼내올 수 있다. */
                System.out.print(rset.getString("emp_name") + " ");
                System.out.println(rset.getString("salary"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            /* 생성과 달리 역순으로 각 스트림을 닫는다. */
            close(rset);
            close(pstmt);
            close(con);
        }
    }

}
