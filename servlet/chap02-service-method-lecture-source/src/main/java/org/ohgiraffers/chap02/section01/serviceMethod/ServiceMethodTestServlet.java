package org.ohgiraffers.chap02.section01.serviceMethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/request-service")
public class ServiceMethodTestServlet extends HttpServlet {

    @Override
    // service Method : 요청 발생 시 매번 호출,
    // 요청이 어떤 방식으로 들어왔는지 판단하여 방식에 맞는 메소드 호출하는 역할
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        /* Http 프로토콜의 정보를 담고 있는 타입으로 다운 캐스팅 한다. */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String httpMethod = request.getMethod(); // 요청 받은 http 메소가 어떤 메소드인지 판단한다.
        System.out.println("httpMethod: " + httpMethod);

        if("GET".equals(httpMethod)) { //Method가 GET이라면 하단 doGet 메소드 호출
            doGet(request, response);
        } else if("POST".equals(httpMethod)) { //Method가 POST라면 하단 doPOST 호출
            doPost(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET 요청을 처리할 메소드 호출");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST 요청을 처리할 메소드 호출");
    }
}
