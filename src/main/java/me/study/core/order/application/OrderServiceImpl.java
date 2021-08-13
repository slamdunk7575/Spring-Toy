package me.study.core.order.application;

import me.study.core.discount.RateDiscountPolicy;
import me.study.core.member.dao.MemberRepository;
import me.study.core.member.dao.MemoryMemberRepository;
import me.study.core.discount.DiscountPolicy;
import me.study.core.member.domain.Member;
import me.study.core.order.domain.Order;

public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository = new MemoryMemberRepository();
    // private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    /**
     * 요구사항 변경에 따라 할인 정책을 RateDiscountPolicy 로 바꾼다면
     *
     * (문제점)
     * 클라이언트(OrderServiceImpl) 코드를 고쳐야 한다.
     *
     * - 역할과 구현을 분리함 -> OK
     * - 다형성을 활용하고 인터페이스와 구현 객체를 분리함 -> OK
     * - DIP, OCP 객체지향 설계 원칙을 준수했는가?
     *   - DIP: 주문서비스 클라이언트(OrderServiceImpl)는 DiscountPolicy 라는 추상(인터페이스)에 의존하지만
     *          RateDiscountPolicy, FixDiscountPolicy 라는 구체(구현)클래스에도 의존하고 있다.
     *
     *   - OCP: 현재 코드는 기능을 확장해서 변경하면 클라이언트(OrderServiceImpl)도 영향을 주어 변경이 되어야 한다.
     *
     * (해결)
     * 인터페이스에만 의존하도록 설계를 변경하자
     * private DiscountPolicy discountPolicy;
     *
     * 누군가 클라이언트(OrderServiceImpl)에 DiscountPolicy의 구현 객체를 대신 생성해서 주입해줘야 한다.
     *
     */
    private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
