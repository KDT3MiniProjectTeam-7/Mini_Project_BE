package com.example.finance7.member.service.Impl;

import com.example.finance7.member.dto.MemberInfoRequest;
import com.example.finance7.member.dto.MemberInfoResponse;
import com.example.finance7.member.entity.Member;
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

    /**
     * 회원정보를 수정한다.
     *
     * @param memberInfoRequest 변경할 회원정보 값들이 있는 dto
     * @param memberId 현재 로그인하고 있는 유저의 memberId
     * @return 회원정보 수정에 성공하면 success, 실패하면 fail를 반환한다.
     */
    @Override
    public String updateMemberInfo(MemberInfoRequest memberInfoRequest, Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).orElseThrow(Exception::new);
            if (!isPasswordValid(member.getPassword(), memberInfoRequest.getOldPassword())) return "fail";
            memberRepository.save(memberInfoRequest.toEntity(member));
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}
