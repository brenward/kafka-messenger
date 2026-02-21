package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.mappers.UserMapper;
import com.bwardweb.kafka_messenger.model.UserDTO;
import com.bwardweb.kafka_messenger.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }
}
