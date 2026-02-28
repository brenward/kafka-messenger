package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.model.MessageDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface MessageService {
    Page<MessageDTO> getChatMessages(UUID chatId, Long sequence);
}
