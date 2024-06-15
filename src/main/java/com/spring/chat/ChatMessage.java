package com.spring.chat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    private String content;
    private String sender;

}