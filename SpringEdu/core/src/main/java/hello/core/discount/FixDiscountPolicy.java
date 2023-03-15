package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000;

    public FixDiscountPolicy() {
    }

    public int discount(Member member, int price) {
        return member.getGrade() == Grade.VIP ? this.discountFixAmount : 0;
    }
}