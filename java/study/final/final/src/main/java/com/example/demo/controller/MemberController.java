package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.form.MemberForm;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    @ResponseBody
    public List<Member> list(Model model){
        return memberService.findMembers();
    }
}
