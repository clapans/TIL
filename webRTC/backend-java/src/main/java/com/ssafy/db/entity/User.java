package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import javax.persistence.*;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class User{
    @Id
    @GeneratedValue
    Long id;
    String username;

    String email;
    String nickname;
    String role;


    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}
