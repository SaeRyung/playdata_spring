package org.ohgiraffers.chap08.section02.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/otherservlet")
public class OtherServletRedirectServlet extends HttpServlet {
    // a tag => get방식
    //redirect > 무조건 get 방식이다. 브라우저 주소 방식
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get 요청 정상 수락");
        resp.setContentType("text/html");

        /* 비즈니스 로직 처리 후 */
        //redirect란 주소값으로 다시 redirect 시키기 -> redirect servlet 필요
        resp.sendRedirect("redirect");
    }
}
