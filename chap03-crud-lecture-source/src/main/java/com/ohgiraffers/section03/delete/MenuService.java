package com.ohgiraffers.section03.delete;

import java.sql.Connection;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuService {
    public void delMenu(Menu menu) {
        Connection con = getConnection();

        MenuRepository menuRepository = new MenuRepository();
        int result = menuRepository.deleteMenu(con,menu);

        if(result > 0){
            commit(con);
        }else{
            rollback(con);
        }
        close(con);

    }

}
