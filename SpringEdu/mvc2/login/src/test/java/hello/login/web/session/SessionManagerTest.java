package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;


class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        // 세션 생성해서 HTTP 응답으로 보냈다고 가정 (서버 -> 클라이언트)
        MockHttpServletResponse response = new MockHttpServletResponse(); // 스프링에서 제공하는 테스트용 서블릿 응답 객체

        Member member = new Member();
        sessionManager.createSession(member, response);

        // 웹 브라우저에서 응답받은 쿠키로 다시 요청한다고 가정 (클라이언트 -> 서버)
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // 클라이언트로부터 받은 세션 조회 (서버)
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();

    }

}