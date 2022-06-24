package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    //회원 검증 메소드 - 미구현
    private void validateDuplicateMember(Member member) {

    }
}
