package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class MenuRepository {
    int deleteMenu(Connection con, Menu menu){
        PreparedStatement ps = null;

        int result = 0;

        Properties prop = new Properties();
        try{
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/section01/insert/mapper/menu-mapper.xml"));
            String sql = prop.getProperty("deleteMenu");

            ps = con.prepareStatement(sql);
            ps.setInt(1, menu.getMenuCode());

            result = ps.executeUpdate();

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
