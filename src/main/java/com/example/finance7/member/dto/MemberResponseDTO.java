package com.example.finance7.member.dto;

import com.example.finance7.member.vo.MemberVO;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
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
     * ResponseDTO 에서 status 받아서 응답용 VO 생성
     *
     * @return
     */
    public MemberVO toVO(){
        return MemberVO.builder()
                .status(status)
                .email(email)
                .name(name)
                .tags(tags)
                .accessToken(accessToken)
                .build();
    }
}
