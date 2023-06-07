package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        // 기존에 프론트 컨트롤러가 하던 파라미터 생성을 어댑터가 대신한다. -> 어댑터는 해당 컨트롤러가 필요한 매개변수에 밪춰서 메서드를 호출한다.
        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        // V4 컨트롤러에 의해 model에 필요한 데이터가 담긴다.
        String viewName = controller.process(paramMap, model);

        // 반환타입이 ModelView이기 때문에 ModelView를 생성해서 반환한다. (어댑터의 역할! - 110V를 220V로 바꾸는 어댑터 기능)
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paraMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paraMap.put(paramName, request.getParameter(paramName)));
        return paraMap;
    }
}
