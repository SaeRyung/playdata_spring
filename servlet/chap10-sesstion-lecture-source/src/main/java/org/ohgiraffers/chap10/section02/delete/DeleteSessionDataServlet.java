package org.ohgiraffers.chap10.section02.delete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/delete")
public class DeleteSessionDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 데이터 꺼내기
        HttpSession session = req.getSession();
        Enumeration<String> keys = session.getAttributeNames();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            System.out.println(key+ "=" + session.getAttribute(key));
        }

        /* 세션 데이터를 제거하는 방법
        * 1. 설정 만료 시간이 지나면 세션 자동 만료
        * 2. removeAttribute() 로 속성 제거
        * 3. invalidate() 로 세션의 모든 데이터 제거(세션 무효화, 강제 만료)
        * */

        System.out.println("=======================================");
        //지워졌는지 확인
        session.removeAttribute("firstName");
        keys = session.getAttributeNames();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            System.out.println(key+ "=" + session.getAttribute(key));
        }

        System.out.println("=======================================");
        session.invalidate();
        keys = session.getAttributeNames();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            System.out.println(key+ "=" + session.getAttribute(key));
        }
    }
}
