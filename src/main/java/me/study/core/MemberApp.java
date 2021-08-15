package me.study.core;

import me.study.core.config.AppConfig;
import me.study.core.member.application.MemberService;
import me.study.core.member.domain.Grade;
import me.study.core.member.domain.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        /**
         * 스프링 컨테이너
         *
         * ApplicationContext를 스프링 컨테이너라 한다.
         * 기존에는 개발자가 AppConfig를 사용해서 직접 객체를 생성하고 DI를 했지만 이제부터 스프링 컨테이너를 사용한다.
         *
         * 스프링 컨테이너는 @Configuration이 붙은 AppConfig를 설정(구성) 정보로 사용한다. 여기서 @Bean 이라 적힌
         * 메소드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. -> 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라고 한다.
         *
         * 스프링 빈은 @Bean이 붙은 메소드 명을 스프링 빈의 이름으로 사용한다. (예: memberService, orderService)
         * 이전에는 개발자가 필요한 객체를 AppConfig를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 객체(스프링 빈)를 찾는다.
         * -> applicationContext.getBean()
         *
         * 기존에 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고 스프링 컨테이너에서
         * 스프링 빈을 찾아서 사용하도록 변경하였다.
         *
         * ...
         * Creating shared instance of singleton bean 'memberService'
         * Creating shared instance of singleton bean 'memberRepository'
         * Creating shared instance of singleton bean 'orderService'
         * Creating shared instance of singleton bean 'discountPolicy'
         *
         * Q. 코드가 약간 더 복잡해진거 같은데 스프링 컨테이너를 사용하면 어떤 장점이 있을까?
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member newMember = new Member(1L, "memberA", Grade.VIP);
        memberService.join(newMember);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + newMember.getName());
        System.out.println("find member : " + findMember.getName());
    }
}
