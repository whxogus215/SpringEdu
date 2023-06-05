package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // *에 어떤 값이 들어와도 이 서블릿이 호출된다는 뜻
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>(); // URL 과 서블릿 매핑

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }
    // 즉, 구현된 컨트롤러가 하는 일은 서비스 로직을 실행하고 ModelView라는 Model에 데이터를 담아서 반환하는 것이다.

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paraMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paraMap, model);

        // viewResolver는 프론트 컨트롤러의 메서드이다!
        MyView view = viewResolver(viewName); // 논리적 이름을 물리적 이름으로 변환시켜서 MyView 객체 반환

        // v3에서 사용하던 ModelView가 없다!
        view.render(model, request, response);
    }
    
    // 논리 이름을 받아서 실제 물리 이름으로 변환하여 MyView를 반환하는 메서드 - View의 폴더 위치가 변경되었을 때, 프론트 컨트롤러만 수정하면 된다.
    private MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    // 메서드 추출 단축키 : 컨트롤 + 알트 + M - 복잡한 로직을 하나의 메서드로 추출하는 방법
    // Http의 요청 정보를 하나의 Map에 저장하여 반환하는 메서드
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paraMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paraMap.put(paramName, request.getParameter(paramName)));
        return paraMap;
    }
}
