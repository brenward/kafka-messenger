package com.bwardweb.kafka_messenger.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private UUID id;

    @NotNull
    @NotBlank
    private String username;
}
