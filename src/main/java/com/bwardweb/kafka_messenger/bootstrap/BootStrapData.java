package com.bwardweb.kafka_messenger.bootstrap;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.repositories.ChatRepository;
import com.bwardweb.kafka_messenger.repositories.MessageRepository;
import com.bwardweb.kafka_messenger.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public BootStrapData(MessageRepository messageRepository, UserRepository userRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        createUsers();
        createChats();
    }

    private void createUsers(){
        if(userRepository.count()==0){
            User user1 = User.builder()
                    .username("Bren")
                    .build();

            User user2 = User.builder()
                    .username("Bob")
                    .build();

            User user3 = User.builder()
                    .username("Dave")
                    .build();

            User user4 = User.builder()
                    .username("Sarah")
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
        }
    }

    private void createChats(){
        if(chatRepository.count()==0){
            Set<User> users = new HashSet<>();
            users.add(userRepository.findByUsernameIgnoreCase("Bren"));
            users.add(userRepository.findByUsernameIgnoreCase("Bob"));

            Chat chat1 = Chat.builder()
                    .chatName("bren_bob")
                    .users(users)
                    .build();

            chatRepository.save(chat1);
        }
    }
}
