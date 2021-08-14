package me.study.core.config;

import me.study.core.discount.DiscountPolicy;
import me.study.core.discount.FixDiscountPolicy;
import me.study.core.member.application.MemberService;
import me.study.core.member.application.MemberServiceImpl;
import me.study.core.member.dao.MemberRepository;
import me.study.core.member.dao.MemoryMemberRepository;
import me.study.core.order.application.OrderService;
import me.study.core.order.application.OrderServiceImpl;

public class AppConfig {

    /**
     * 관심사의 분리
     * 문제점: 클라이언트(예: OrderServiceImpl)에서 주문 생성, 구체 클래스 생성(MemberRepository, DiscountPolicy) 등
     * 다양한 책임을 가지고 있다.
     *
     * 해결: 구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스를 만들자!
     * - AppConfig를 통해서 관심사를 분리
     * - AppConfig는 구체 클래스를 선택한다. 애플리케이션 동작의 전체 구성을 책임진다.
     * - 이제 클라이언트(예: OrderServiceImpl)객체는 기능을 실행하는 책임만 수행하면 된다.
     */
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    /**
     * AppConfig 리팩토링
     * 문제점: 현재 AppConfig를 보면 중복이 있고 (new MemoryMemberRepository()) '역할'에 따른 '구현'이 한눈에 안보인다.
     *
     * 해결: 역할을 분리하자 -> AppConfig를 보면 역할과 구현 클래스가 한눈에 들어온다.
     * 애플리케이션 전체 구성이 어떻게 되어있는지 확인할 수 있다.
     * 현재는 예: MemoryMemberRepository를 사용하지만 나중에 변경을 한다면 memberRepository() 만 변경하면 된다.
     */
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }


}
