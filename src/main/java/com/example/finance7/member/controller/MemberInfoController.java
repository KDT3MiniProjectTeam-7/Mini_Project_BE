package com.example.finance7.member.controller;

import com.example.finance7.member.dto.KeywordRequest;
import com.example.finance7.member.dto.SomeMemberUpdateInfoRequest;
import com.example.finance7.member.dto.StatusResponse;
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
@RequestMapping("/")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    /**
     * 일부 회원정보를 조회한다.
     *
     * @return 현재 로그인 중인 member email, name, age, tags 정보와 status 정보 반환 (response dto)
     */
    @GetMapping("user")
    public StatusResponse findSomeMemberInfo() {
        return memberInfoService.findSomeMemberInfo();
    }

    /**
     * 회원정보를 수정한다.
     *
     * @param memberUpdateInfoRequest 변경할 회원정보 값 (request dto)
     * @return status 정보 반환 (response dto)
     */
    @PatchMapping("user")
    public StatusResponse updateSomeMemberInfo(@RequestBody SomeMemberUpdateInfoRequest memberUpdateInfoRequest) {
        return memberInfoService.updateSomeMemberInfo(memberUpdateInfoRequest.toDto());
    }


    @PostMapping("user/keywords")
    public StatusResponse selectAllrecentKewords(@RequestBody KeywordRequest keywordRequest, HttpServletRequest request) {
        return memberInfoService.addRecentKeyword(keywordRequest.getSearchContent(), request.getHeader(HttpHeaders.AUTHORIZATION));
    }
    @GetMapping("user/keywords")
    public MemberSearchHistoryResponseVO selectRecentSearchKeyWords() {
        return memberInfoService.selectRecentSearchKeyWords();
    }
}
