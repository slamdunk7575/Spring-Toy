package me.study.core.discount;

import me.study.core.member.domain.Grade;
import me.study.core.member.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int fixDiscountAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return fixDiscountAmount;
        }
        return 0;
    }
}
