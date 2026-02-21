package com.bwardweb.kafka_messenger.mappers;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.UserDTO;

public interface UserMapper {
    UserDTO mapUserToUserDTO(User user);
}
