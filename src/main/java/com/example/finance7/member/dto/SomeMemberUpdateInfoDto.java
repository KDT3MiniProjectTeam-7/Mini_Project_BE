package com.example.finance7.member.dto;

import com.example.finance7.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SomeMemberUpdateInfoDto {
    private String name;
    private String oldPassword;
    private String newPassword;

    /**
     *변경해야 하는 정보를 담은 전체 회원정보 Entity로 변환한다.
     *
     * @param member 변경전 전체 회원 정보 (Entity)
     * @return 변경후 전체 회원 정보 (Entity)
     */
    public Member toEntityWithUpdate(Member member) {
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
