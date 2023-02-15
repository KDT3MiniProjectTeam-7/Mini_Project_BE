package com.example.finance7.member.service.impl;

import com.example.finance7.config.JwtProvider;
import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.dto.MemberResponseDTO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.entity.Scession;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisTemplate redisTemplate;


    @Override
    public Member findMemberByMemberId(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (!member.isPresent()) {
            throw new NullPointerException("회원을 찾을 수 없습니다.");
        }
        else {
            return member.get();
        }
    }

    /**
     * 로그인 기능 수행 및 Access Token 발급 및 redis 저장
     *
     * @param memberRequestDTO
     * @return
     */
    @Override
    public MemberResponseDTO doLogin(MemberRequestDTO memberRequestDTO) {
        Member requestMember = memberRequestDTO.toEntity();

        Optional<Member> optionalMember = memberRepository.findByEmail(
                memberRequestDTO.getEmail()
        );

        try {
            Member responseMember = optionalMember.get();

            if (!isMatchPassword(requestMember, responseMember)) {
                throw new NoSuchElementException();
            }
            if (!isOpenUser(responseMember)) {
                throw new NoSuchElementException();
            }

            String accessToken = jwtProvider.token(responseMember.getEmail());

            redisTemplate.opsForValue()
                    .set(accessToken, jwtProvider.tokenToMember("Bearer "+accessToken).getExpiration().toString());

            return responseMember.toDTO("success", jwtProvider.token(responseMember.getEmail()));
        } catch (NoSuchElementException e) {
            return MemberResponseDTO.builder()
                    .status("failed : Email/Password 불일치")
                    .build();
        }
    }

    /**
     * 평문 비밀번호 , 암호화 비밀번호 비교 메소드
     *
     * @param requestMember  요청받은 비밀번호 암호화가 되지 않은 Member Entity
     * @param responseMember DB 에서 응답받은 비밀번호 암호화가 된 Member Entity
     * @return
     */
    public boolean isMatchPassword(Member requestMember, Member responseMember) {
        return passwordEncoder.matches(requestMember.getPassword(), responseMember.getPassword());
    }

    /**
     * 계정 상태 확인 메소드
     *
     * @param member
     * @return Open : true , Close : false
     */
    public boolean isOpenUser(Member member){
        return member.getSecession().equals(Scession.OPEN);
    }
}
