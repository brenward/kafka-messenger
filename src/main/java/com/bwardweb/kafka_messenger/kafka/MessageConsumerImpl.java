package com.bwardweb.kafka_messenger.kafka;

import com.bwardweb.kafka_messenger.constants.Constants;
import com.bwardweb.kafka_messenger.entities.Message;
import com.bwardweb.kafka_messenger.mappers.MessageMapper;
import com.bwardweb.kafka_messenger.model.MessageDTO;
import com.bwardweb.kafka_messenger.repositories.ChatRepository;
import com.bwardweb.kafka_messenger.repositories.MessageRepository;
import com.bwardweb.kafka_messenger.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
@Component
public class MessageConsumerImpl implements MessageConsumer {
    private final KafkaConsumer<String, MessageDTO> kafkaConsumer;

    private final MessageRepository messageRepository;

    private final ChatRepository chatRepository;

    private final UserRepository userRepository;

    public MessageConsumerImpl(KafkaConsumer<String, MessageDTO> kafkaConsumer, MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.kafkaConsumer = kafkaConsumer;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void startConsumer() {
        log.info("Getting reference to the main thread to control shutdown");
        final Thread mainThread = Thread.currentThread();

        log.info("Adding shutdown hook");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                log.info("Shutdown exiting: calling callout.wakeup");
                kafkaConsumer.wakeup();

                log.info("Joingin main thread to allow execution of code in main thread");
                try {
                    mainThread.join();
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });

        log.info("subscribe to topic");
        kafkaConsumer.subscribe(Arrays.asList(Constants.MESSAGE_TOPIC));

        try {
            while (true) {
                log.info("Polling");
                ConsumerRecords<String, MessageDTO> records = kafkaConsumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, MessageDTO> record : records) {
                    log.info("Key: {} Value: {}", record.key(), record.value());

                    Message message = Message.builder()
                            .chat(chatRepository.getReferenceById(record.value().getChatId()))
                            .sequence(messageRepository.findMaxSequenceByChatId(record.value().getChatId()) + 1)
                            .user(userRepository.getReferenceById(record.value().getUserId()))
                            .message(record.value().getMessage())
                            .build();
                    messageRepository.save(message);
                    log.info("Message saved with id: {}", message.getMessageId());
                }
            }
        }catch (WakeupException ex){
            log.info("Consumer is starting to shutdown");
        }catch (Exception ex){
            log.info("Unexpected exception, ex");
        }finally {
            kafkaConsumer.close();
            log.info("Consumer shutdown gracefully");
        }
    }
}
