package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

// 실제 CRUD - 기능 구현
// MenuDAO: DB의 특정 CRUD 작업을 할 메소드 단위를 작성
// 수행할 sql 구문, name과 id값으로 처리하여 가져온다.

public class MenuDAO {
    public List<MenuDTO> selectAllMenu(SqlSession sqlSession) {
        /* 메소드의 첫 번째 인자로 어떠한 SQL 구문을 수행할 것인지를 전달한다.
         * Mapper의 namespace와 id로 구분한다. */
        return sqlSession.selectList("MenuMapper.selectAllMenu");
        //selectList 메소드를 통해 Sql 구문 실행하고, 실행된 결과를 알맞게 반환해서 List로 반환 내포
    }



    public MenuDTO selectMenuByMenuCode(SqlSession sqlSession, int menuCode) {
        /* 메소드의 두 번째 인자로 SQL 구문을 수행할 때 필요한 객체를 전달한다.
        * 조회조건으로 menuCode 사용해야 하기 때문에 두번째 인자값으로 넘겨준다. */
        return sqlSession.selectOne("MenuMapper.selectMenuByMenuCode", menuCode);
    }



    public int insetMenu(SqlSession sqlSession, MenuDTO menu) {
        return sqlSession.insert("MenuMapper.insertMenu", menu);
    }



    public int deleteMenu(SqlSession sqlSession, int menu) {
        return sqlSession.delete("MenuMapper.deleteMenu", menu);
    }


    public int modifyMenu(SqlSession sqlSession, MenuDTO menu) {
        return sqlSession.update("MenuMapper.modifyMenu", menu);
    }
}