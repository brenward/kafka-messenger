package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.mappers.ChatMapper;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import com.bwardweb.kafka_messenger.model.UserDTO;
import com.bwardweb.kafka_messenger.repositories.ChatRepository;
import com.bwardweb.kafka_messenger.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    public ChatServiceImpl(ChatRepository chatRepository, ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public List<ChatDTO> getChatsByUser(User user) {
        if(user.getChats().size() == 0){
            return null;
        }

        return user.getChats().stream()
                .map(chatMapper::mapChatToChatDTO)
                .toList();
    }
}
