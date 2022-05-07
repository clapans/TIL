package com.thirdproject.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/index")
    public String wow(){
        return "index.html";
    }

    @GetMapping("/wow")
    public String holy(){
        String value = "holys";
        return value;
    }
}
