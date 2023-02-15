package com.example.finance7.member.controller;

import com.example.finance7.member.service.impl.MemberServiceImpl;
import com.example.finance7.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    /**
     * 로그인 기능 수행 메서드
     * @param memberVO
     * @return
     */
    @PostMapping("/login")
    public MemberVO doLogin(@RequestBody MemberVO memberVO){
        MemberVO responseVO = memberService.doLogin(memberVO.toDTO()).toVO();

        return responseVO;
    }
}
