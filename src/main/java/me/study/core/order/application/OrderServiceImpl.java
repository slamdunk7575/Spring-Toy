package me.study.core.order.application;

import me.study.core.member.dao.MemberRepository;
import me.study.core.member.dao.MemoryMemberRepository;
import me.study.core.discount.DiscountPolicy;
import me.study.core.discount.FixDiscountPolicy;
import me.study.core.member.domain.Member;
import me.study.core.order.domain.Order;

public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
