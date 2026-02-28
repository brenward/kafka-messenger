package com.bwardweb.kafka_messenger.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@ToString
public class MessageDTO {
    private UUID messageId;

    private Long sequence;

    @NotNull
    @NotBlank
    private UUID chatId;

    private String chatName;

    @NotNull
    @NotBlank
    private UUID userId;

    private String username;

    @NotNull
    @NotBlank
    private String message;
}
