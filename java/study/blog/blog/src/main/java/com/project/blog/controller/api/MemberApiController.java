package com.project.blog.controller.api;

import com.project.blog.domain.Member;
import com.project.blog.domain.Role;
import com.project.blog.dto.ResponseDto;
import com.project.blog.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {
    private final MemberRepository memberRepository;

    public MemberApiController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/api/member")
    public ResponseDto<Integer> memberCreate(@RequestBody Member member){
        memberRepository.save(member);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }
}
