package com.bwardweb.kafka_messenger.kafka;

import com.bwardweb.kafka_messenger.model.MessageDTO;

public interface MessageProducer {
    boolean sendMessage(MessageDTO message);
}
