package com.example.finance7.member.dto;

import com.example.finance7.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfoResponse {

    private String email;
    private String name;
    private String tags;
    private String status;

    public MemberInfoResponse(String status) {
        this.status = status;
    }

    public MemberInfoResponse(Member member, String status) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.tags = member.getTags();
        this.status = status;
    }
}
