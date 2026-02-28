package com.bwardweb.kafka_messenger.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ChatDTO {
    private UUID id;

    @NotNull
    @NotBlank
    private String chatName;

    private Set<MessageDTO> messages;
}
