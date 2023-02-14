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
        MemberVO responseVO = memberService.doLogin(memberVO.toDTO())
                .toVO("success");

        return responseVO;
    }

    /**
     * 로그인 시 비밀번호 불일치 / 없는 계정 등 Exception 처리
     * @param e
     * @return
     */
    @ExceptionHandler(NoSuchElementException.class)
    public MemberVO handleNoSuchElementException(NoSuchElementException e){
        return MemberVO.builder()
                .status(e.getMessage())
                .build();
    }
}
