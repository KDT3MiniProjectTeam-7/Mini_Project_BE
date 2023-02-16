package com.example.finance7.member.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SomeMemberInfoResponse extends StatusResponse {

    private String email;
    private String name;
    private int age;
    private String tags;
}
