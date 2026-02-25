package com.bwardweb.kafka_messenger.controller;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import com.bwardweb.kafka_messenger.services.ChatService;
import com.bwardweb.kafka_messenger.services.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final UserService userService;
    private final ChatService chatService;

    public ChatController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @RequestMapping(value = "/{username}")
    public List<ChatDTO> getChatsByUserName(@PathVariable String username) {
        User user = userService.getUserByName(username);

        if (user == null) {
            return new ArrayList<>();
        }

        return chatService.getChatsByUser(user);
    }
}
