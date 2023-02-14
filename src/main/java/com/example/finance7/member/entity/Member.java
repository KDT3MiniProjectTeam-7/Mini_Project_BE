package com.example.finance7.member.entity;

import com.example.finance7.member.dto.MemberResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEMBERS")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "EMAIL", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Column(name = "NAME", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(500)", nullable = false)
    private String password;

    @Column(name = "BIRTHDAY", columnDefinition = "DATE")
    private Date birthDay;

    @CreatedDate
    @Column(name = "SIGNUP_DATE", columnDefinition = "DATETIME")
    private LocalDateTime signUpDate;

    @Column( name = "SECESSION", columnDefinition = "VARCHAR(255) DEFAULT 'OPEN'")
    @Enumerated(EnumType.STRING)
    private Scession secession;

    @Column(name = "TAGS", columnDefinition = "TEXT")
    private String tags;

    /**
     * Token 을 받아 응답 DTO 로 생성
     * @param accessToken
     * @return
     */
    public MemberResponseDTO toDTO(String accessToken){
        return MemberResponseDTO.builder()
                .memberId(String.valueOf(memberId))
                .email(email)
                .name(name)
                .password(password)
                .birthDay(String.valueOf(birthDay))
                .signUpDate(String.valueOf(signUpDate))
                .secession(String.valueOf(secession))
                .tags(tags)
                .accessToken(accessToken)
                .build();
    }
}
