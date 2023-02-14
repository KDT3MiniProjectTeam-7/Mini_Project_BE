package com.example.finance7.member.dto;

import io.jsonwebtoken.Claims;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDTO {
    String memberId;
    String email;
    String name;
    String password;
    String birthDay;
    String signUpDate;
    String secession;
    String tags;

}
