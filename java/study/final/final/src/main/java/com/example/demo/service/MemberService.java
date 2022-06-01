package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long join(Member member){
        validName(member.getName());
        return memberRepository.save(member).getId();
    }

    public void validName(String name){
        memberRepository.findByName(name).
                ifPresent(x -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
