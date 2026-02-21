package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.ChatDTO;

import java.util.List;

public interface ChatService {
    List<ChatDTO> getChatsByUser(User user);
}
