package com.example.finance7.member.controller;

import com.example.finance7.member.dto.SomeMemberInfoDto;
import com.example.finance7.member.dto.SomeMemberUpdateInfoRequest;
import com.example.finance7.member.dto.StatusResponse;
import com.example.finance7.member.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
        try {
            SomeMemberInfoDto memberInfoDto = memberInfoService.findSomeMemberInfo();

            return memberInfoDto.toResponse("success");
        } catch (NullPointerException | NoSuchElementException e) {
            log.error(e.getMessage());

            return StatusResponse.builder()
                    .status("fail")
                    .build();
        }
    }

    /**
     * 회원정보를 수정한다.
     *
     * @param memberUpdateInfoRequest 변경할 회원정보 값 (request dto)
     * @return status 정보 반환 (response dto)
     */
    @PatchMapping("user")
    public StatusResponse updateSomeMemberInfo(@RequestBody SomeMemberUpdateInfoRequest memberUpdateInfoRequest) {
        String status = memberInfoService.updateSomeMemberInfo(memberUpdateInfoRequest.toDto());

        return StatusResponse.builder()
                .status(status)
                .build();
    }
}
