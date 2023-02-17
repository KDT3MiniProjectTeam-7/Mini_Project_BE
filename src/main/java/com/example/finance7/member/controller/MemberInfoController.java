package com.example.finance7.member.controller;

import com.example.finance7.member.dto.MemberInfoUpdateRequestDTO;
import com.example.finance7.member.dto.StatusResponseDTO;
import com.example.finance7.member.service.MemberInfoService;
import com.example.finance7.member.vo.MemberSearchHistoryResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    /**
     * 회원 정보 조회
     *
     * @return email, name, age, tags 정보와 status 정보 반환 (response dto)
     */
    @GetMapping("/user")
    public StatusResponseDTO findMemberInfo(HttpServletRequest request) {
        return memberInfoService.findMemberInfo(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    /**
     * 회원 정보 수정
     *
     * @param updateRequest 변경할 회원 정보 값 (request dto)
     * @return status 정보 반환 (response dto)
     */
    @PatchMapping("/user")
    public StatusResponseDTO updateMemberInfo(@RequestBody MemberInfoUpdateRequestDTO updateRequest, HttpServletRequest request) {
        return memberInfoService.updateMemberInfo(updateRequest, request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    @GetMapping("user/keywords")
    public MemberSearchHistoryResponseVO selectRecentSearchKeyWords () {
        return memberInfoService.selectRecentSearchKeyWords();
    }
}
