package com.bwardweb.kafka_messenger.bootstrap;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.entities.Message;
import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import com.bwardweb.kafka_messenger.repositories.ChatRepository;
import com.bwardweb.kafka_messenger.repositories.MessageRepository;
import com.bwardweb.kafka_messenger.repositories.UserRepository;
import com.bwardweb.kafka_messenger.services.ChatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ChatService chatService;

    public BootStrapData(MessageRepository messageRepository, UserRepository userRepository, ChatRepository chatRepository, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.chatService = chatService;
    }


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        createUsers();
        createChats();
        createMessages();
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

    private void createMessages() {
            User user1 = userRepository.findByUsernameIgnoreCase("Bren");
            User user2 = userRepository.findByUsernameIgnoreCase("Bob");
            Set<User> users = new HashSet<>();
            users.add(user1);
            users.add(user2);
            ChatDTO chatDTO = chatService.getChatByUserCollection(users);

            if(chatDTO != null && chatDTO.getMessages().size() <= 0){
                Chat chat = chatRepository.getReferenceById(chatDTO.getId());
                Message message1 = Message.builder()
                        .message("hello")
                        .sequence(1L)
                        .user(user1)
                        .chat(chat)
                        .build();

                Message message2 = Message.builder()
                        .message("hello back")
                        .sequence(2L)
                        .user(user2)
                        .chat(chat)
                        .build();

                Message message3 = Message.builder()
                        .message("Hows it going")
                        .sequence(3L)
                        .user(user1)
                        .chat(chat)
                        .build();

                messageRepository.save(message1);
                messageRepository.save(message2);
                messageRepository.save(message3);

            }

    }
}
