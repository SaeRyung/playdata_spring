package org.ohgiraffers.chap10.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(); //세션 꺼내기
        // 요청을 타고 온 쿠키내(세션id)에서
        System.out.println("redirect session id: " + session.getId());

        // getAttribute > object type이므로 String 변환
        /* 세션 인스턴스에 저장 된 attribute는 다른 요청에서 꺼내서 쓸 수 있다. */
        String firstName = (String)session.getAttribute("firstName");
        String lastName = (String)session.getAttribute("lastName");
        System.out.println(firstName +  " " +lastName);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1> your firstName is " + firstName + " and lastName is " + lastName + "</h1>");
        out.flush();
        out.close();
    }
}
