package com.project.blog.controller;

import com.project.blog.domain.Member;
import com.project.blog.domain.Role;
import com.project.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
public class MemberController {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/dummy/join")
    public String join(Member member){
        System.out.println(member.getUsername());
        System.out.println(member.getPassword());
        System.out.println(member.getEmail());
        member.setRole(Role.User);
        memberRepository.save(member);
        return "완료";
    }

    @GetMapping("/dummy/user/{id}")
    public Member detail(@PathVariable int id){
        Member member = memberRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저가 없습니다. id : " + id);
            }
        });
        return member;
    }
}
