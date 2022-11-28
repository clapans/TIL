package com.example.myweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPostReq {
    private String title;
    private String period;
    private String personnel;
    private String introduce;
    private String role;
    private String process;
    private String mainFunction;
    private String learned;
    private List<String> environment;
}
