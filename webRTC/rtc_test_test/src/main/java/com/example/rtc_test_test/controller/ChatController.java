package com.example.rtc_test_test.controller;

import com.example.rtc_test_test.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        messagingTemplate.convertAndSend("/topic/public/" + chatMessage.getContent(), chatMessage);
    }
}