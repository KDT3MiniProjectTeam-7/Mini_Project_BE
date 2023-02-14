package com.example.finance7.member.vo;

import com.example.finance7.member.dto.MemberRequestDTO;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    String status;

    String memberId;
    String email;
    String name;
    String password;
    String birthDay;
    String signUpDate;
    String secession;
    String tags;

    String accessToken;

    /**
     * Request 받은 VO 를 DTO 로 변환
     * @return
     */
    public MemberRequestDTO toDTO(){
        return MemberRequestDTO.builder()
                .memberId(memberId)
                .email(email)
                .name(name)
                .password(password)
                .birthDay(birthDay)
                .signUpDate(signUpDate)
                .secession(secession)
                .tags(tags)
                .build();
    }
}
