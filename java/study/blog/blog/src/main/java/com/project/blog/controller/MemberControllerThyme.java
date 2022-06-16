package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberControllerThyme {
    @GetMapping({"","/"})
    public String index(){
        return "index";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "Member/LoginForm";
    }

    @GetMapping("/member/join")
    public String joinForm(){
        return "Member/JoinForm";
    }


}
