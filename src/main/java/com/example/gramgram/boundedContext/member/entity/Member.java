package com.example.gramgram.boundedContext.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)  //@CreatedDate, @LastModifiedDate가 작동하게 허용
@ToString
@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate    //아래 column에는 값이 자동으로 들어간다(INSERT 할 때마다)
    private LocalDateTime createDate;
    @LastModifiedDate   //아래 column에는 값이 자동으로 들어간다(UPDATE 할 때마다)
    private LocalDateTime modifyDate;
    @Column(unique = true)
    private String username;
    private String password;

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("member"));   //모든 회원은 멤버 권한을 갖게 되고

        if ("admin".equals(username)) {     //이름이 admin인 회원은 admin 권한을 갖게됨
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        }

        return grantedAuthorities;
    }
}
