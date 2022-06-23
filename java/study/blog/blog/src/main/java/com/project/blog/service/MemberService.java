package com.project.blog.service;

import com.project.blog.domain.Member;
import com.project.blog.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public int join(Member member){
        try{
            memberRepository.save(member);
            return 1;
        }catch(Exception e){
            System.out.println("에러가 발생했습니다.");
        }
        return -1;
    }
}
