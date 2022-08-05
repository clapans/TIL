package com.ssafy.db.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String questionContent;
    private String level;
    private String audioUrl;
}
