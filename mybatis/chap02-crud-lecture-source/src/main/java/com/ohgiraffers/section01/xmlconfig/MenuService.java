package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

// Service 로직
// MenuService : 트랜잭션 단위를 묶어서 메소드화 하는 역할, DB와 연동할 커넥션 생성 후 commit, rollback 처리 역할

public class MenuService {

    private final MenuDAO menuDAO;

    public MenuService() {
        this.menuDAO = new MenuDAO();
    }



    public List<MenuDTO> selectAllMenu() {
        SqlSession sqlSession = getSqlSession(); //DB 연결
        // CRUD 기능 위해 MenuDTO 에 날리기
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);

        sqlSession.close(); //sqlSession은 하나의 요청당 하나의 수행, 닫아줘야한다.

        return menuList; // 그 후 menuList로 반환 > MenuController 쪽으로
    }



    // 새로운 요청이기에 sqlSession 다시 요청
    public MenuDTO selectMenuByMenuCode(int menuCode) {
        SqlSession sqlSession = getSqlSession();

        MenuDTO menu = menuDAO.selectMenuByMenuCode(sqlSession, menuCode);

        sqlSession.close();

        return menu;
    }


    public boolean registMenu(MenuDTO menu){
        SqlSession sqlSession = getSqlSession();
        // insert, update, delete 된 행의 갯수를 반환받으므로 int type
        int result = menuDAO.insetMenu(sqlSession, menu);
        if(result > 0){
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0; // true, false 반환

    }




    public boolean deleteMenu(int menu) {
        SqlSession sqlSession = getSqlSession();
        int result = menuDAO.deleteMenu(sqlSession, menu);
        if(result > 0){
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0;
    }



    public boolean modifyMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();
        int result = menuDAO.modifyMenu(sqlSession, menu);
        if(result > 0){
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        sqlSession.close();
        return result > 0;
    }
}