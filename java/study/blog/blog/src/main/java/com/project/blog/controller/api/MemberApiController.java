package com.project.blog.controller.api;

import com.project.blog.domain.Member;
import com.project.blog.domain.Role;
import com.project.blog.dto.ResponseDto;
import com.project.blog.repository.MemberRepository;
import com.project.blog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final HttpSession httpSession;

    @Autowired
    public MemberApiController(MemberService memberService, HttpSession httpSession) {
        this.memberService = memberService;
        this.httpSession = httpSession;
    }


    @PostMapping("/api/member")
    public ResponseDto<Integer> memberCreate(@RequestBody Member member){
        member.setRole(Role.User);
        memberService.join(member);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }

    /*
    @PostMapping("/api/member/login")
    public ResponseDto<Integer> memberlogin(@RequestBody Member member){
        Member principal = memberService.login(member);

        if (principal != null)
            httpSession.setAttribute("principal", principal);

        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }*/
}
