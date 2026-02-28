package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.Message;
import com.bwardweb.kafka_messenger.mappers.MessageMapper;
import com.bwardweb.kafka_messenger.model.MessageDTO;
import com.bwardweb.kafka_messenger.repositories.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public Page<MessageDTO> getChatMessages(UUID chatId, Long sequence) {
        PageRequest pageRequest = PageRequest.of(0, 100, Sort.by(Sort.Direction.ASC, "sequence"));

        Page<Message> messagePage;

        if(sequence != null && sequence > 1){
            messagePage = messageRepository.findByChatIdAndSequence(chatId, sequence, pageRequest);
        }else{
            messagePage = messageRepository.findByChatId(chatId, pageRequest);
        }

        return messagePage.map(messageMapper::mapMessageToMessageDTO);
    }
}
