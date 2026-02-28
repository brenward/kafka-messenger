package com.bwardweb.kafka_messenger.kafka;

import com.bwardweb.kafka_messenger.model.MessageDTO;
import com.bwardweb.kafka_messenger.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageProducerImpl implements MessageProducer {
    private final KafkaProducer<String, MessageDTO> kafkaProducer;

    public MessageProducerImpl(KafkaProducer<String, MessageDTO> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public boolean sendMessage(MessageDTO message) {
        ProducerRecord<String, MessageDTO> record = new ProducerRecord<>(Constants.MESSAGE_TOPIC, message);
        try{
            kafkaProducer.send(record);
            return true;
        }catch (Exception e){
            log.error("Error sending message: {}", e.getMessage());
            return false;
        }
    }
}
