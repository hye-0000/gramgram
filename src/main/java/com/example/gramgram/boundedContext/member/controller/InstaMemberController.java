package com.example.gramgram.boundedContext.member.controller;

import com.example.gramgram.base.rq.Rq;
import com.example.gramgram.base.rsData.RsData;
import com.example.gramgram.boundedContext.member.entity.InstaMember;
import com.example.gramgram.boundedContext.member.service.InstaMemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instaMember")
@RequiredArgsConstructor
public class InstaMemberController {
    private final Rq rq;
    @Autowired
    private InstaMemberService instaMemberService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/connect")
    public String showConnect() {
        return "usr/instaMember/connect";
    }

    @AllArgsConstructor
    @Getter
    public static class ConnectForm{
        @NotBlank
        @Size(min = 4, max = 30)
        private final String username;

        @NotBlank
        @Size(min = 1, max = 1)
        private final String gender;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/connect")
    public String connect(@Valid ConnectForm connectForm){
        RsData<InstaMember> rsData = instaMemberService.connect(rq.getMember(), connectForm.getUsername(), connectForm.getGender());
        return rq.redirectWithMsg("/pop", "인스타그램 계정이 연결되었습니다.");
    }
}