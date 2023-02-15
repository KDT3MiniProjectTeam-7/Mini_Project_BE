package com.example.finance7.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SomeMemberInfoDto {

    private String email;
    private String name;
    private int age;
    private String tags;

    /**
     * 일부 회원정보 dto를 일부 회원정보와 상태 정보 response dto로 변환한다.
     *
     * @param status 상태 (String)
     * @return 일부 회원정보와 상태 정보 (response dto)
     */
    public SomeMemberInfoAndStatusResponse toResponse(String status) {
        return SomeMemberInfoAndStatusResponse.builder()
                .status(status)
                .email(email)
                .name(name)
                .age(age)
                .tags(tags)
                .build();
    }
}
