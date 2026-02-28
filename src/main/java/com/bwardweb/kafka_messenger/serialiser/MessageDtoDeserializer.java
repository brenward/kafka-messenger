package com.bwardweb.kafka_messenger.serialiser;

import com.bwardweb.kafka_messenger.model.MessageDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

public class MessageDtoDeserializer implements Deserializer<MessageDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MessageDTO deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), MessageDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }
}
