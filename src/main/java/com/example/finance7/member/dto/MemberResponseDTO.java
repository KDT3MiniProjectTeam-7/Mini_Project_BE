package com.example.finance7.member.dto;

import com.example.finance7.member.vo.MemberVO;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
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
     * ResponseDTO 에서 status 받아서 응답용 VO 생성
     * @param status
     * @return
     */
    public MemberVO toVO(String status){
        return MemberVO.builder()
                .status(status)
                .memberId(memberId)
                .email(email)
                .name(name)
                .password(password)
                .birthDay(birthDay)
                .signUpDate(signUpDate)
                .secession(secession)
                .tags(tags)
                .accessToken(accessToken)
                .build();
    }
}
