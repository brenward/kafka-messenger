package com.bwardweb.kafka_messenger.mappers;

import com.bwardweb.kafka_messenger.entities.Message;
import com.bwardweb.kafka_messenger.model.MessageDTO;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperImpl implements MessageMapper {
    @Override
    public MessageDTO mapMessageToMessageDTO(Message message) {
        return MessageDTO.builder()
                .messageId(message.getMessageId())
                .message(message.getMessage())
                .username(message.getUser().getUsername())
                .userId(message.getUser().getId())
                .chatName(message.getChat().getChatName())
                .chatId(message.getChat().getId())
                .sequence(message.getSequence())
                .build();
    }
}
