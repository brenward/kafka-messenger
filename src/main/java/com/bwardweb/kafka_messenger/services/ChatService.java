package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ChatService {
    List<ChatDTO> getChatsByUser(User user);

    ChatDTO getChatByUserCollection(Set<User> users);

    ChatDTO getChatById(UUID uuid);

    long getMaxSequenceOfMessages(UUID chatId);
}
