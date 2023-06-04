package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paraMap) {
        // 전달받은 매개변수인 paraMap에는 Http 요청 정보가 들어있다.
        String username = paraMap.get("username");
        int age = Integer.parseInt(paraMap.get("age"));

        // 클라이언트가 입력한 값을 실제 Member로 생성하여 저장소에 저장
        Member member = new Member(username, age);
        memberRepository.save(member);
    
        // 서비스 로직이 끝났고, 이제 View 영역으로 넘길 차례 -> JSP 뿌려줘야할 때
        ModelView mv = new ModelView("save-result");
        // ModelView에 논리적 이름을 저장하고, 추가로 뷰에서 필요한 데이터를 담는다.(저장소에 새로 저장한 member에 대한 데이터)
        mv.getModel().put("member", member);
        return mv;
    }
}
