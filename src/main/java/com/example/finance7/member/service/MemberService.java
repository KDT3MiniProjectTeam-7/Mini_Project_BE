package com.example.finance7.member.service;

import com.example.finance7.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Member findMemberByMemberId(Long memberId);
}
