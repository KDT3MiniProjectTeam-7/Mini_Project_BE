package com.example.finance7.member.service.Impl;

import com.example.finance7.member.dto.MemberInfoResponse;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberRepository memberRepository;

    /**
     *회원정보를 조회한다.
     *
     * @param memberId 현재 로그인하고 있는 유저의 memberId
     * @return memberId가 유효하면 유저의 email, name, tags와 상태 "success"를 dto에 넣어 반환하고, 유효하지 않으면 상태 "fail"만 dto에 넣어 반환한다.
     */
    @Override
    public MemberInfoResponse findMemberInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .map(member -> new MemberInfoResponse(member, "success"))
                .orElse(new MemberInfoResponse("fail"));
    }

}
