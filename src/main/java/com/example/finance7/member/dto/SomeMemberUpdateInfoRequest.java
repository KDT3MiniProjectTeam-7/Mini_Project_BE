package com.example.finance7.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SomeMemberUpdateInfoRequest {

    private String name;
    private String oldPassword;
    private String newPassword;

    /**
     * service 전달을 위해 request dto를 dto로 변환한다.
     *
     * @return 변경할 회원정보 값 (dto)
     */
    public SomeMemberUpdateInfoDto toDto() {
        return SomeMemberUpdateInfoDto.builder()
                .name(name)
                .oldPassword(oldPassword)
                .newPassword(newPassword)
                .build();
    }
}
