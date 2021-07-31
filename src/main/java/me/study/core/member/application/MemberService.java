package me.study.core.member.application;

import me.study.core.member.domain.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
