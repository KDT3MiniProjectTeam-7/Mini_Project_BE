package com.example.finance7.member.controller;

import com.example.finance7.member.dto.StatusResponseDTO;
import com.example.finance7.member.service.MemberSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberSurveyController {

    private final MemberSurveyService memberSurveyService;

    /**
     * 최초 테스트 요청 하는 메서드
     */
    @PostMapping("/tags")
    public StatusResponseDTO insertMemberTags(String[] tags, HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return memberSurveyService.enterMemberTags(tags, header);
    }

    /**
     * 위 메서드와 기능 동일 요청 url 만 다름
     */
    @PutMapping("/tags/put")
    public StatusResponseDTO updateMemberTags(String[] tags, HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return memberSurveyService.enterMemberTags(tags, header);
    }

}
