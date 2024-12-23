package com.base.app.main.controller;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api")
@RestController 
public class ChatController {

    private final ChatClient chatClient;
    
    ChatController(ChatClient.Builder builder) {
      this.chatClient = builder.build();
    }
    @GetMapping("/chat")
    Map<String, String> completion(@RequestParam String message) {
        return Map.of(
                "답변",
                chatClient.prompt().user(message).call().content()
        );
    }
}