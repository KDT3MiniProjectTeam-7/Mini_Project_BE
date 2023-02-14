package com.example.finance7.member.service;

import com.example.finance7.member.dto.MemberInfoRequest;
import com.example.finance7.member.dto.MemberInfoResponse;

public interface MemberInfoService {
    MemberInfoResponse findMemberInfo(Long memberId);

    String updateMemberInfo(MemberInfoRequest memberInfoRequest, Long memberId);

    boolean isPasswordValid(String memberPassword, String oldPassword);
}
