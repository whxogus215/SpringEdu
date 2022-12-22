package hello.core.member;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public MemberServiceImpl() {
    }

    public void join(Member member) {
        this.memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return this.memberRepository.findById(memberId);
    }
}
