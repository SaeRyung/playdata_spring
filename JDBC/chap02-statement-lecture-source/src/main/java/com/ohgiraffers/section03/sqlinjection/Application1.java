package com.ohgiraffers.section03.sqlinjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    private static String empId = "200";
    private static String empName = "' or 1=1 and emp_id='200"; // 무조건 true가 되는 조건

    public static void main(String[] args) {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        // 입력값 empId, empName 변수화 처리
        String query = "select * from employee where emp_id ='" + empId + "' and emp_name = '" + empName + "'";


        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query); //실행하는 순간 query 전달

            if(rset.next()) {
                System.out.println(rset.getString("emp_name") + " 님 환영합니다.");
            } else{
                System.out.println("해당 사원은 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
