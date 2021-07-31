package me.study.core.member.dao;

import me.study.core.member.domain.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
