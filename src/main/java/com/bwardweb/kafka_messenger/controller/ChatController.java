package com.bwardweb.kafka_messenger.controller;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import com.bwardweb.kafka_messenger.services.ChatService;
import com.bwardweb.kafka_messenger.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final UserService userService;
    private final ChatService chatService;

    public ChatController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{username}")
    public List<ChatDTO> getChatsByUserName(@PathVariable String username) {
        User user = userService.getUserByName(username);

        if (user == null) {
            return new ArrayList<>();
        }

        return chatService.getChatsByUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/chat/{id}")
    public ChatDTO getChatById(@PathVariable(required = true) String id) {
        return chatService.getChatById(UUID.fromString(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/chat/refreshed/{id}")
    public boolean sequenceGreaterThanCurrent(@PathVariable(required = true) String id, @RequestParam(required = true) Long sequence) {
        long currentMaxSequence = chatService.getMaxSequenceOfMessages(UUID.fromString(id));

        if(currentMaxSequence < 1){
            return false;
        }
        
        return chatService.getMaxSequenceOfMessages(UUID.fromString(id)) < sequence;
    }
}
