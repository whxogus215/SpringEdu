package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 전달받은 매개변수인 paraMap에는 Http 요청 정보가 들어있다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 클라이언트가 입력한 값을 실제 Member로 생성하여 저장소에 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 프론트 컨트롤러가 직접 모델을 넘기기 때문에 구현 컨트롤러는 v3에서 사용한 ModelView 객체를 생성해서 넘겨주지 않아도 된다.
        model.put("member", member);
        return "save-result";
    }
}
