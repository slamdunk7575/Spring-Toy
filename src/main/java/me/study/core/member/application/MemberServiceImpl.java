package me.study.core.member.application;

import me.study.core.member.dao.MemberRepository;
import me.study.core.member.dao.MemoryMemberRepository;
import me.study.core.member.domain.Member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
