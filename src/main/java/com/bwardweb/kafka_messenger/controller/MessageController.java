package com.bwardweb.kafka_messenger.controller;

import com.bwardweb.kafka_messenger.kafka.MessageProducer;
import com.bwardweb.kafka_messenger.model.MessageDTO;
import com.bwardweb.kafka_messenger.services.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
public class MessageController {

    private final MessageService messageService;

    private final MessageProducer messageProducer;

    public MessageController(MessageService messageService, MessageProducer messageProducer) {
        this.messageService = messageService;
        this.messageProducer = messageProducer;
    }

    //Get Messages for chat
    @RequestMapping(value = "/message/list", method = RequestMethod.GET)
    public Page<MessageDTO> getMessagesByChatId(@RequestParam(required = true) String chatId, @RequestParam(required = false) Long sequence) {
        return messageService.getChatMessages(UUID.fromString(chatId),sequence);
    }

    //Post Message to chat
    @PostMapping(value= "/message")
    public ResponseEntity createMessage(@RequestBody MessageDTO messageDTO) {
        //Validate message (chatId exists & user is part of chat)


        //Call kafka service to put into Kafka
        if(messageProducer.sendMessage(messageDTO)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
