package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); 구현체가 어떤 저장소를 선택하지 직접 결정하는 코드(좋지 않음)

    // 수정된 코드에는 인터페이스만을 갖고 있음 (DIP 지킴)
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        this.memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return this.memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
