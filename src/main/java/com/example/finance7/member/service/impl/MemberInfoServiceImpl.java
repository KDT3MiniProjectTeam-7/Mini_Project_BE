package com.example.finance7.member.service.impl;

import com.example.finance7.member.dto.SomeMemberUpdateInfoDto;
import com.example.finance7.member.dto.StatusResponse;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberRepository memberRepository;
    private final MemberServiceImpl memberService;

    /**
     *전체 회원정보를 조회한다.
     *
     * @return 현재 로그인 중인 member 전체 정보 반환 (Entity)
     */
    @Override
    public Member findAllMemberInfo() throws NullPointerException, NoSuchElementException {
        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("존재하지 않는 회원입니다."));

        if (!memberService.isOpenUser(member)) {
            throw new NoSuchElementException("이미 탈퇴한 회원입니다.");
        }
        return member;
    }

    /**
     * 일부 회원정보를 조회한다.
     *
     * @return 현재 로그인 중인 member의 email, name, age, tags 정보 반환 (dto)
     */
    @Override
    public StatusResponse findSomeMemberInfo() {
        try {
            Member member = findAllMemberInfo();
            int age = calculateAge(member.getBirthDay());

            return member.toSomeMemberInfoResponse("success", age);
        } catch (NullPointerException | NoSuchElementException e) {
            log.error(e.getMessage());

            return makeStatusResponse("fail");
        }

    }

    /**
     * 회원정보를 수정한다.
     *
     * @param memberUpdateInfoDto 변경할 회원정보 값 (dto)
     * @return 회원정보 수정 성공 시 success, 실패 시 fail 반환 (String)
     */
    @Override
    public String updateSomeMemberInfo(SomeMemberUpdateInfoDto memberUpdateInfoDto) {
        try {
            Member responseMember = findAllMemberInfo();
            Member requestMember = memberUpdateInfoDto.toEntityWithUpdate(responseMember);

            if (!memberService.isMatchPassword(requestMember, responseMember)) {
                return "fail";
            }

            memberUpdateInfoDto.setNewPassword(memberService.encodePassword(memberUpdateInfoDto.getNewPassword()));
            memberRepository.save(memberUpdateInfoDto.toEntityWithUpdate(responseMember));
        } catch (NullPointerException | NoSuchElementException e) {
            log.error(e.getMessage());

            return "fail";
        }
        return "success";
    }

    /**
     * 나이를 계산한다.
     *
     * @param birthday 생일날짜시간 (Date)
     * @return 나이 (int)
     */
    @Override
    public int calculateAge(Date birthday) {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - (birthday.getYear() + 1900);

        if (today.get(Calendar.MONTH) > birthday.getMonth()) {
            return age;
        }

        if (today.get(Calendar.MONTH) == birthday.getMonth() && today.get(Calendar.DATE) >= birthday.getDate()) {
            return age;
        }
        return age - 1;
    }

    /**
     * 상태 코드 response dto 생성
     *
     * @param status 상태 (String)
     * @return 상태 정보 반환 (response dto)
     */
    @Override
    public StatusResponse makeStatusResponse(String status) {
        return StatusResponse.builder()
                .status(status)
                .build();
    }
}
