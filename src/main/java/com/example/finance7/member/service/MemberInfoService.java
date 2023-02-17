package com.example.finance7.member.service;

import com.example.finance7.member.dto.SomeMemberUpdateInfoDto;
import com.example.finance7.member.dto.StatusResponse;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.vo.MemberSearchHistoryResponseVO;

import java.util.Date;

public interface MemberInfoService {
    Member findAllMemberInfo();

    StatusResponse findSomeMemberInfo();

    StatusResponse updateSomeMemberInfo(SomeMemberUpdateInfoDto requestDto);

    int calculateAge(Date birthday);

    StatusResponse makeStatusResponse(String status);

    MemberSearchHistoryResponseVO selectRecentSearchKeyWords();
}