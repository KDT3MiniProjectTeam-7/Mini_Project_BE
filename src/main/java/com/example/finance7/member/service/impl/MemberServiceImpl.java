package com.example.finance7.member.service.impl;

import com.example.finance7.config.JwtProvider;
import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.dto.MemberResponseDTO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.member.service.MemberService;
import lombok.RequiredArgsConstructor;
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

    /**
     * 로그인 기능 수행 및 Access Token 발급
     * @param memberRequestDTO
     * @return
     */
    @Override
    public MemberResponseDTO doLogin(MemberRequestDTO memberRequestDTO) {
        Optional<Member> optionalMember = memberRepository.findByEmail(
                memberRequestDTO.getEmail()
        );

        try{
            Member member = optionalMember.get();

            if (!passwordEncoder.matches(memberRequestDTO.getPassword(),member.getPassword())){
                System.out.println("Not match");
                throw new NoSuchElementException("Not Found");
            }

            return member.toDTO(jwtProvider.token(member.getEmail()));
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Not Found");
        }
    }

    /**
     *
     * @param memberRequestDTO
     * @return
     */
    private MemberRequestDTO encodePassword(MemberRequestDTO memberRequestDTO){
        memberRequestDTO.setPassword(
                passwordEncoder.encode(memberRequestDTO.getPassword())
        );

        return memberRequestDTO;
    }
}
