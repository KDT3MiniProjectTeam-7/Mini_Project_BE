package com.example.finance7.member.service.impl;

import com.example.finance7.member.entity.Member;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberByMemberId(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (!member.isPresent()) {
            throw new NullPointerException("회원을 찾을 수 없습니다.");
        }
        else {
            return member.get();
        }
    }
}
