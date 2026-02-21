package com.bwardweb.kafka_messenger.mappers;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import org.springframework.stereotype.Component;

@Component
public class ChatMapperImpl implements ChatMapper {
    @Override
    public ChatDTO mapChatToChatDTO(Chat chat) {
        return ChatDTO.builder()
                .id(chat.getId())
                .chatName(chat.getChatName())
                .build();
    }
}
