package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*") // *에 어떤 값이 들어와도 이 서블릿이 호출된다는 뜻
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>(); // URL 과 서블릿 매핑

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
    // 즉, 구현된 컨트롤러가 하는 일은 서비스 로직을 실행하고 ModelView라는 Model에 데이터를 담아서 반환하는 것이다.

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paraMap = createParamMap(request);

        // URI에 매핑된 컨트롤러의 process() 수행
        // 서비스 로직이 끝나고, 실제 추가된 member 객체에 대한 정보가 담긴 ModelView 반환
        ModelView mv = controller.process(paraMap);
        
        // ModelView : 논리적인 이름, Request 요청정보에 대한 값들 갖고 있다. (Request 대신에 사용되는 Model이다.)
        String viewName = mv.getViewName(); // 컨트롤러가 입력한 논리적 View 이름

        // viewResolver는 프론트 컨트롤러의 메서드이다!
        MyView view = viewResolver(viewName); // 논리적 이름을 물리적 이름으로 변환시켜서 MyView 객체 반환
        
        // ModelView에 있는 Map에 새로 저장된 member에 대한 정보가 들어 있으므로, 렌더링하는 함수에 같이 전달해야 한다.
        // 기존에는 request 객체에 직접 담아서 전달했다면 이번에는 ModelView를 사용했기 때문
        view.render(mv.getModel(), request, response);
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
