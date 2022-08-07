package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void 회원가입() throws Exception {
        //given
        UserRegisterPostReq userRegisterInfo=new UserRegisterPostReq();
        userRegisterInfo.setUsername("user");
        userRegisterInfo.setPassword("password");
        userRegisterInfo.setEmail("user@naver.com");
        userRegisterInfo.setNickname("nickname");
        userRegisterInfo.setRole("student");

        //when
        User user=userService.createUser(userRegisterInfo);
        //then
        assertThat(userRegisterInfo.getUsername()).isEqualTo(user.getUsername());
        assertThat(userRegisterInfo.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        UserRegisterPostReq userRegisterInfo=new UserRegisterPostReq();
        userRegisterInfo.setUsername("user");
        userRegisterInfo.setPassword("password");
        userRegisterInfo.setEmail("user@naver.com");
        userRegisterInfo.setNickname("nickname");
        userRegisterInfo.setRole("student");

        //when
        User user=userService.createUser(userRegisterInfo);
        //then
        assertThatThrownBy(() -> userService.createUser(userRegisterInfo)).isInstanceOf(IllegalStateException.class);
    }




}