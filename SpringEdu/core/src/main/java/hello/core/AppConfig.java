package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  // 스프링에서 등록할 때 AppConfig에 달아주는 어노테이션
public class AppConfig {

    // 생성자를 통해서 객체가 생성되어 주입되는 코드임 -> 생성자 주입
    /* 생성자 주입 작성법
        1. 인터페이스 타입으로 선언한다.
        2. 생성자를 만들어서 참조 변수를 연결한다.
        3. AppConfig를 통해 만든 생성자를 호출함과 동시에 그 매개변수로 새로운 객체를 넣어준다.(주입)
     */
    // AppConfig라는 외부에서 구현체를 결정하기 때문에 이제 각각의 구현체들은 자신들이 수행하는 메서드에만 집중할 수 있게 된다.(관심사 분리)

    // 이 안에 역할과 구현이 정확하게 나뉘어 있음. (메서드들이 어떤 역할을 하며, 어떤 구현체를 반환하는 지에 대해서)
    @Bean // 스프링 컨테이너에 등록되는 어노테이션
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }


}
