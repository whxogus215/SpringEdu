package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 각종 파라미터를 받는다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 서비스 로직을 호출한다. (저장소)
        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보관한다. - request 객체의 내부 저장소를 Model로 사용
        request.setAttribute("member", member);

        // View에 결과 값을 넘겨준다.
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
