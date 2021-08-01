package me.study.core.member;

import me.study.core.member.application.MemberService;
import me.study.core.member.application.MemberServiceImpl;
import me.study.core.member.domain.Grade;
import me.study.core.member.domain.Member;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member newMember = new Member(1L, "memberA", Grade.VIP);
        memberService.join(newMember);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + newMember.getName());
        System.out.println("find member : " + findMember.getName());
    }
}
