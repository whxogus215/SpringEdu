package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request : 웹 브라우저에서 요청한 메세지를 기반으로 객체를 생성한 것, response : 서버가 다시 WAS에 전달할 내용을 담는 Http 응답 객체
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        // 서블릿의 Http 요청 객체를 통하여 쿼리 파라미터의 값을 쉽게 가져올 수 있음. 사용자가 직접 파싱하지 않아도 됨
        System.out.println("username = " + username);

        // Http Content-Type에 들어가는 정보
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        // Http 응답 메세지
        response.getWriter().write("hello " + username);
    }
}
