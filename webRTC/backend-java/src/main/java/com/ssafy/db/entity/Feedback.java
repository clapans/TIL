package com.ssafy.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
public class Feedback{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name="script_id")
    private Script script;

    @CreationTimestamp
    private Timestamp created_at;

}