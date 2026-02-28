package com.bwardweb.kafka_messenger.controller;

import com.bwardweb.kafka_messenger.model.MessageDTO;
import com.bwardweb.kafka_messenger.services.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //Get Messages for chat
    @RequestMapping(value = "/message/list", method = RequestMethod.GET)
    public Page<MessageDTO> getMessagesByChatId(@RequestParam(required = true) String chatId, @RequestParam(required = false) Long sequence) {
        return messageService.getChatMessages(UUID.fromString(chatId),sequence);
    }

    //Post Message to chat
    @PostMapping()
    public ResponseEntity createMessage(@RequestBody MessageDTO messageDTO) {
        //Call kafka service to put into Kafka



        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
