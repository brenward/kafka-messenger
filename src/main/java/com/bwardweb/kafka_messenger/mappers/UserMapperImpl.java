package com.bwardweb.kafka_messenger.mappers;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO mapUserToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
