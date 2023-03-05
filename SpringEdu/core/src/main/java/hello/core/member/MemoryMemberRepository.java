package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap();

    public MemoryMemberRepository() {
    }

    public void save(Member member) {
        store.put(member.getId(), member);
    }

    public Member findById(Long memberId) {
        return (Member)store.get(memberId);
    }
}