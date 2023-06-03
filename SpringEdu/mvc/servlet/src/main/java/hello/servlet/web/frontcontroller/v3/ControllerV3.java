package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paraMap); // 서블릿 객체가 파라미터로 들어가지 않음 - 컨트롤러가 서블릿에 종속되지 않음
}
