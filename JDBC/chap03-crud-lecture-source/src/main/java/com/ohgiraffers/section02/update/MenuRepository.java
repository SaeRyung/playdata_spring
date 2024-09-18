package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class MenuRepository {
    int updateMenu(Connection con, Menu menu){
        PreparedStatement ps = null;
        // 1. PreparedStatement 를 통해

        int result = 0;

        Properties props = new Properties();
        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/section01/insert/mapper/menu-mapper.xml"));
            String sql = props.getProperty("updateMenu");

            ps = con.prepareStatement(sql); // 2. 수행하고 싶은 sql 셋팅
            // 3. 셋팅된 구문에서 물음표 위치에 따라 전달받은 객체
            ps.setInt(1, menu.getMenuCode());
            ps.setString(2, menu.getMenuName());
            ps.setInt(3, menu.getMenuPrice());

            result = ps.executeUpdate(); // 4. 실행

            } catch (IOException e) {
            throw new RuntimeException(e);
            } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
        }
        return result;
    }
}
