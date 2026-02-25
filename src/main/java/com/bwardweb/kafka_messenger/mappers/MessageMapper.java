package com.bwardweb.kafka_messenger.mappers;

import com.bwardweb.kafka_messenger.entities.Message;
import com.bwardweb.kafka_messenger.model.MessageDTO;

public interface MessageMapper {
    MessageDTO mapMessageToMessageDTO(Message message);
}
