package com.bwardweb.kafka_messenger.mappers;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChatMapperImpl implements ChatMapper {
    private final MessageMapper messageMapper;

    public ChatMapperImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public ChatDTO mapChatToChatDTO(Chat chat) {
        return ChatDTO.builder()
                .id(chat.getId())
                .chatName(chat.getChatName())
                .messages(chat.getMessages().stream()
                        .map(messageMapper::mapMessageToMessageDTO)
                        .collect(Collectors.toSet()))
                .build();
    }
}
