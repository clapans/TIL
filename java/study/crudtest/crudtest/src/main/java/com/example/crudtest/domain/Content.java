package com.example.crudtest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String path;
    private String title;
    private String password;

    @Builder
    public Content(String path, String title, String password){
        super();
        this.path = path;
        this.title = title;
        this.password = password;
    }
}
