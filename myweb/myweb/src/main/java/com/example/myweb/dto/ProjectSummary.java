package com.example.myweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectSummary {
    private Long id;
    private String title;
    private String image;

    public ProjectSummary(Long id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }
}
