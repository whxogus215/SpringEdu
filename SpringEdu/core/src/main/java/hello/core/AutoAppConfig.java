package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // @Configuration이 붙은 것들은 제외하기 (충돌 방지 - 중복)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // AppConfig는 항상 프로젝트의 최상단에 두기 (컴포넌트 스캔 대상 : AppConfig를 기준으로 함)
)
public class AutoAppConfig {

}
