package com.example.finance7.member.dto;

import com.example.finance7.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfoRequest {

    private String name;
    private String oldPassword;
    private String newPassword;

    public Member toEntity(Member member) {
        return Member.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(name)
                .password(newPassword)
                .birthDay(member.getBirthDay())
                .signUpDate(member.getSignUpDate())
                .secession(member.getSecession())
                .tags(member.getTags())
                .build();
    }
}
