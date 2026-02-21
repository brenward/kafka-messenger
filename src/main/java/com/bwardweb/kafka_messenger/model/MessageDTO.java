package com.bwardweb.kafka_messenger.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MessageDTO {
    @NotNull
    @NotBlank
    private UUID messageId;

    @NotNull
    private Long sequence;

    @NotNull
    @NotBlank
    private UUID chatId;

    @NotNull
    @NotBlank
    private String chatName;

    @NotNull
    @NotBlank
    private UUID userId;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String message;
}
