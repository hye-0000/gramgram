package com.example.gramgram.boundedContext.member.service;

import com.example.gramgram.boundedContext.member.entity.Member;
import com.example.gramgram.boundedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)     //아래 메소드들이 전부 readonly라는 것을 명시
                                    //DB를 여러 개 사용할 때 select만으로 이뤄진 메소드라는 것을 명시
                                    // 다른 연산을 하려면 메소드 위에 @Transactional을 붙여주면 됨
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional  //join은 insert를 유발하기 때문에
    public Member join(String username, String password) {
        Member member = Member
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        return memberRepository.save(member);
    }
}
