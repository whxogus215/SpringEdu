package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    // 철저하게 인터페이스만 의존하고 있음, 구현체가 어떤 것으로 올지 전혀 알 수가 없음.
    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy; // final을 사용하면 기본 값을 할당하거나 생성자를 만들어야 함, 인터페이스만 의존하도록 함, 관심사 분리를 위해서 구현체를 선언하지 않음

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = this.memberRepository.findById(memberId);
        int discountPrice = this.discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
