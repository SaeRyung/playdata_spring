package org.ohgiraffers.chap01.section02.annotation;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet; // annotation > 클래스를 서블릿으로 등록
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

/* * 매핑방법
    2. annotation
     * */

/* loadOnStartup: 서버 시작 시점에 인스턴스 생성 및 초기화 메소드 호출
* 전달하는 숫자는 우선 순위로 숫자가 낮을 수록 우선 순위가 높다. */
@WebServlet(value = "/annotation-lifecycle", loadOnStartup = 1)
// 클래스가 서블릿과 연결되었다.
//ㄴ index.jsp에서 요청 시 응답할 수 있는 WebServlet template, 템플릿 안에서 메세지 응답한다.
public class LifeCycleTest extends HttpServlet {

    /* 기본 생성자 */
    public LifeCycleTest() {
        System.out.println("annotation 생성자 호출");
    }

    /* init : 최초 서블릿 요청 시 객체를 생성하며 초기화 작업을 할 수 있는 메소드 */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("annotation 매핑 init 호출");
    }

    /* service: 서블릿 컨테이너에 의해 호출 되며 최초 요청 시에는 init 이후 동작, 두 번째 요청부터는 init 없이 바로 동작하는 메소드*/
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("annotation 매핑 service 호출");
    }

    /* destory: 컨터이너가 종료될 때 호출 되는 메소드로 주로 자원을 반납하는 용도로 사용 되는 메소드*/
    @Override
    public void destroy() {
        System.out.println("annotation 매핑 destroy 호출");
    }
}
