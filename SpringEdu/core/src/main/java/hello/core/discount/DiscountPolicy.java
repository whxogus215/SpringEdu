package hello.core.discount;

public interface DiscountPolicy {
    int discount(hello.core.member.Member member, int i);
}