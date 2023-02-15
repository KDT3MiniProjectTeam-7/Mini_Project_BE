package com.example.finance7.member.service;

import com.example.finance7.member.dto.SomeMemberInfoDto;
import com.example.finance7.member.dto.SomeMemberUpdateInfoDto;
import com.example.finance7.member.entity.Member;

import java.util.Date;

public interface MemberInfoService {
    Member findAllMemberInfo();

    SomeMemberInfoDto findSomeMemberInfo();

    String updateSomeMemberInfo(SomeMemberUpdateInfoDto requestDto);

    int calculateAge(Date birthday);
}
