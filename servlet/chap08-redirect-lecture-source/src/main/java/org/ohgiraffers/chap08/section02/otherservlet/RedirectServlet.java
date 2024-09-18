package org.ohgiraffers.chap08.section02.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("이 servlet으로 redirect 완료");

        //한글값 깨지지 않게 민타입 선언
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>이 서블릿으로 redirect 완료!</h1>");
        out.flush();
        out.close();
    }
}
