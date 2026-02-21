package com.bwardweb.kafka_messenger.mappers;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.model.ChatDTO;

public interface ChatMapper {
    ChatDTO mapChatToChatDTO(Chat chat);
}
