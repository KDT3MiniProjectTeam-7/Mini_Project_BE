package com.example.finance7.member.service;

import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.dto.MemberResponseDTO;
import com.example.finance7.member.entity.Member;

public interface MemberService {

    public MemberResponseDTO doLogin(MemberRequestDTO memberRequestDTO);

    Member findMemberByMemberId(Long memberId);
}
