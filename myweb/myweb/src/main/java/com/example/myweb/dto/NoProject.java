package com.example.myweb.dto;

import com.example.myweb.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoProject {
    private Long id;
    private String title;
    private String image;
    private String period;
    private String personnel;
    private String introduce;
    private String role;
    private String process;
    private String mainFunction;
    private String learned;
    private List<Environment> environment;
}
