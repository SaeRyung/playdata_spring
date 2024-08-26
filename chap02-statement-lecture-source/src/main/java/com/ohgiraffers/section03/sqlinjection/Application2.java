package com.ohgiraffers.section03.sqlinjection;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

/* Statement와 달리 PreparedStatement는 placeholder를 통한 sql injection 공격을 막을 수 있게 작성 되어 있어
* 보안적인 측면에서도 더 우수하다. */
public class Application2 {

    private static String empId = "200";
    private static String empName = "' or 1=1 and emp_id='200"; // 무조건 true가 되는 조건

    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // PreparedStatement: 온전히 완성된 구문을 넣어야 한다.
        String query = "select * from employee where emp_id = ? and emp_name = ?";
        /* placeholder 에 싱글쿼테이션이 들어가면 추가적으로 싱글쿼테이션을 넣어 sql injection 공격을 막는다.
        *  select * from employee where emp_id = '200' and emp_name = ''' or 1=1 and emp_id = ''200'; */


        try {
            // 동작 시 내부 체킹을 함께 한다. String 값 하나를 꺼내는 동작
            pstmt = con.prepareStatement(query); // 전달된 구문은 파싱되므로 동일한 구문 재요청시 파싱내용을 캐싱하여 쓰기 때문에 성능 좋음.
            pstmt.setString(1, empId);
            pstmt.setString(2, empName);

            rset = pstmt.executeQuery(); //query 미리 전달했으므로 실행 시 쿼리문 전달 X

            if(rset.next()) {
                System.out.println(rset.getString("emp_name") + " 님 환영합니다.");
            } else{
                System.out.println("해당 사원은 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
