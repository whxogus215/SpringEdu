package hello.servlet.web.frontcontroller.v5;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 기존의 컨트롤러와 비교했을 때, 하나의 컨트롤러 타입이 들어가지 않고 Object 타입이 들어간다. -> 모든 컨트롤러 타입이 들어갈 수 있다는 뜻
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

}
