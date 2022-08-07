package com.ssafy.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    private Script script;

    @CreationTimestamp
    private Timestamp createdAt;

    private boolean state; // 디폴트 값 false

    private String room;
}