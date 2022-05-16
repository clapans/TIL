package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String nice(Model model) {
        model.addAttribute("username","박수근");
        return "greeting";
    }

    @GetMapping("/bye")
    public String ong(Model model) {
        model.addAttribute("nickname","박");
        return "goodbye";
    }
}
