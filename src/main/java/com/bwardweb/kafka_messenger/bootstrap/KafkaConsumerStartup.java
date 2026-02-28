package com.bwardweb.kafka_messenger.bootstrap;

import com.bwardweb.kafka_messenger.kafka.MessageConsumer;
import com.bwardweb.kafka_messenger.model.MessageDTO;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerStartup implements CommandLineRunner {
    private final MessageConsumer messageConsumer;

    public KafkaConsumerStartup(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @Override
    public void run(String... args) throws Exception {
        messageConsumer.startConsumer();
    }
}
