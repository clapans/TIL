package com.example.myweb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity{

    private String title;
    @Lob
    private String image;
    private String period;
    private String personnel;
    @Column(length = 2000)
    private String introduce;
    @Column(length = 2000)
    private String role;
    @Column(length = 2000)
    private String process;
    @Column(length = 2000)
    private String mainFunction;
    @Column(length = 2000)
    private String learned;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<Environment> environment;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<Images> images;

    public Project(String title, String period, String personnel, String introduce, String role, String process, String mainFunction, String learned) {
        this.title = title;
        this.period = period;
        this.personnel = personnel;
        this.introduce = introduce;
        this.role = role;
        this.process = process;
        this.mainFunction = mainFunction;
        this.learned = learned;
    }
}
