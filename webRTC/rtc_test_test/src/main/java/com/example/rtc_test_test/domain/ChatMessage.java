package com.example.rtc_test_test.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
}