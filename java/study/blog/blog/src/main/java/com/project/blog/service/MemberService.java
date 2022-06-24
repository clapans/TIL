package com.project.blog.service;

import com.project.blog.domain.Member;
import com.project.blog.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void join(Member member){
        try{
            memberRepository.save(member);
        }catch(Exception e){
            throw new IllegalArgumentException("이미 사용자가 있는 아이디입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void login(Member member, HttpSession session){
        Member principal = memberRepository.findByUsernameAndPassword(member.getUsername(), member.getPassword()).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("일치하는 아이디 혹은 비밀번호가 없습니다.");
            }
        });
        session.setAttribute("principal",principal);
    }
}
