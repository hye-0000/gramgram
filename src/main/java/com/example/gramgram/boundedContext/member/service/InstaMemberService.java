package com.example.gramgram.boundedContext.member.service;

import com.example.gramgram.base.rsData.RsData;
import com.example.gramgram.boundedContext.member.entity.InstaMember;
import com.example.gramgram.boundedContext.member.entity.Member;
import com.example.gramgram.boundedContext.member.repository.InstaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstaMemberService {
    private final InstaMemberRepository instaMemberRepository;

    public Optional<InstaMember> findByUsername(String username) {
        return instaMemberRepository.findByUsername(username);
    }

    public RsData<InstaMember> connect(Member member, String username, String gender) {
        if (findByUsername(username).isPresent()) {
            return RsData.of("F-1", "해당 인스타그램 아이디는 이미 다른 사용자와 연결되었습니다.");
        }

        RsData<InstaMember> instaMemberRsData = create(username, gender);

        return instaMemberRsData;
    }

    private RsData<InstaMember> create(String username, String gender) {
        InstaMember instaMember = InstaMember
                .builder()
                .username(username)
                .gender(gender)
                .build();

        instaMemberRepository.save(instaMember);

        return RsData.of("S-1", "인스타계정이 등록되었습니다.", instaMember);
    }
}