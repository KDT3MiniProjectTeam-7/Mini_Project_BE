package com.example.finance7.member.controller;

import com.example.finance7.member.dto.MemberInfoRequest;
import com.example.finance7.member.dto.MemberInfoResponse;
import com.example.finance7.member.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    /**
     * 회원정보를 조회한다.
     *
     * @return 로그인하고 있는 유저의 memberId로 유저의 email, name, tags와 상태 (success 또는 fail)를 dto에 넣어 반환한다.
     */
    @GetMapping("user")
    public MemberInfoResponse listMemberInfo() {
        String memberId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return memberInfoService.findMemberInfo(Long.parseLong(memberId));
    }

    /**
     * 회원정보를 수정한다.
     *
     * @param memberInfoRequest 변경할 회원정보 값들이 있는 dto
     * @return 회원정보 수정에 성공하면 success, 실패하면 fail 반환한다.
     */
    @PatchMapping("user")
    public String updateMemberInfo(@RequestBody MemberInfoRequest memberInfoRequest) {
        String memberId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return memberInfoService.updateMemberInfo(memberInfoRequest, Long.parseLong(memberId));
    }
}
