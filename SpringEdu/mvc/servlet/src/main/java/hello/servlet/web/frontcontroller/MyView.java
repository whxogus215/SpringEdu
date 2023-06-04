package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    /** 기존에는 HTTP Request에 렌더링에 필요한 데이터들을 저장했지만 이제는 ModeView(Model)에 데이터를 저장하기 때문에
        렌더링하는 과정에서 ModelView에 저장된 Map(실제 ModelView에서 렌더링에 필요한 데이터가 저장된 곳)이 추가로 필요하다.
     **/
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        // jsp는 request 객체에 있는 값을 조회해서 데이터를 출력하기 때문에 request에 setAttribute 하는 작업이 필요하다.
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
