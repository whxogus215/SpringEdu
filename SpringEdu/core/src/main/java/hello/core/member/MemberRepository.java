package hello.core.member;

public interface MemberRepository {
    void save(Member var1);

    Member findById(Long var1);
}
