package com.ohgiraffers.section01.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement ps = null;

        /* DML(insert, update, delete)
        * 수행 결과가 int 타입으로 반환된다. (수행 된 행의 개수) */
        int result = 0;

        String sql = "insert into tbl_menu(menu_name, menu_price, category_code, orderable_status) + values(?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "봉골레청국장");
            ps.setInt(2, 50000);
            ps.setInt(3, 4);
            ps.setString(4, "Y");

            result = ps.executeUpdate();  // select 시에는 executeQuery, dml 구문은 executeUpate

            if(result>0){
                System.out.println("insert 결과 : " + result);
                con.commit();
            }else{
                con.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(con);
        }

    }
}
