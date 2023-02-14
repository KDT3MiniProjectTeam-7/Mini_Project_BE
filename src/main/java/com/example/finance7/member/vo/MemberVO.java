package com.example.finance7.member.vo;

import com.example.finance7.member.dto.MemberRequestDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    String status;

    String email;
    String name;
    String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthDay;
    String tags;

    String accessToken;

    /**
     * Request 받은 VO 를 DTO 로 변환
     * @return
     */
    public MemberRequestDTO toDTO(){
        return MemberRequestDTO.builder()
                .email(email)
                .name(name)
                .password(password)
                .birth(birthDay)
                .tags(tags)
                .build();
    }
}
