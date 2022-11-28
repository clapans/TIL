package com.example.myweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Environment extends BaseEntity{
    private String content;
    @Lob
    private String image;
    private String Category;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="project")
    private Project project;

    public Environment(String content, Project project) {
        this.content = content;
        this.project = project;
    }
}
