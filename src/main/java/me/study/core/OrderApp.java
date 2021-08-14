package me.study.core;

import me.study.core.config.AppConfig;
import me.study.core.member.application.MemberService;
import me.study.core.member.domain.Grade;
import me.study.core.member.domain.Member;
import me.study.core.order.application.OrderService;
import me.study.core.order.domain.Order;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println(order);
    }
}
