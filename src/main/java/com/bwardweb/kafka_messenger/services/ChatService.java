package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.ChatDTO;

import java.util.List;
import java.util.Set;

public interface ChatService {
    List<ChatDTO> getChatsByUser(User user);

    ChatDTO getChatByUserCollection(Set<User> users);
}
