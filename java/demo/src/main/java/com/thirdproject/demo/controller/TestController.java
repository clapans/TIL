package com.thirdproject.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/index")
    public String wow(){
        return "index.html";
    }
}
