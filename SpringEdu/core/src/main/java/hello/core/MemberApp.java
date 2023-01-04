package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); // AppConfig라는 외부 객체를 통해 구현체를 생성한다. 클라이언트가 직접 생성하지 않음.

        // AppConfig 클래스에 @Bean으로 등록된 객체들을 다 스프링 컨테이너에서 관리하게 됨
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        /// getBean의 name은 @Bean으로 등록된 메서드 이름이며, 반환하는 타입도 같이 적어준다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP); 
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
