package me.study.core.order.application;

import me.study.core.member.application.MemberService;
import me.study.core.member.application.MemberServiceImpl;
import me.study.core.member.domain.Grade;
import me.study.core.member.domain.Member;
import me.study.core.order.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private MemberService memberService = new MemberServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    @DisplayName("주문을 생성할 수 있다.")
    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        System.out.println(order);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        assertThat(order.calculatePrice()).isEqualTo(9000);
    }

}
