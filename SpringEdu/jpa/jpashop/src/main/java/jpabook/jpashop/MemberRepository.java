package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 저장하는 메서드의 경우, 가급적 반환값을 안 만드는 것을 권장 (사이드 이펙트 방지 : "커맨드와 쿼리를 분리해라(?)")
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
