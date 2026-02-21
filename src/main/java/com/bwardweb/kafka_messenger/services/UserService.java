package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.UserDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {
    User getUserByName(String username);
}
