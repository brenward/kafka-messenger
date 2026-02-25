package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.mappers.ChatMapper;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import com.bwardweb.kafka_messenger.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    public ChatServiceImpl(ChatRepository chatRepository, ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public List<ChatDTO> getChatsByUser(User user) {
        if(user.getChats().size() == 0){
            return null;
        }

        return user.getChats().stream()
                .map(chatMapper::mapChatToChatDTO)
                .toList();
    }

    @Override
    public ChatDTO getChatByUserCollection(Set<User> users) {

        //Get All Chats for all users
        Set<Chat> allChats = users.stream()
                .flatMap(user -> user.getChats().stream())
                .collect(Collectors.toSet());

        //Check if any chat contains all users and no more
        List<Chat> matchingChat = allChats.stream()
                .filter(chat -> chatBelongsToUsers(chat,users))
                .toList();

        if(matchingChat.size() != 1){
            return null;
        }
        return chatMapper.mapChatToChatDTO(matchingChat.getFirst());
    }

    private boolean chatBelongsToUsers(Chat chat, Set<User> users) {
        if(chat.getUsers().size() != users.size()){
            return false;
        }

        return chat.getUsers().containsAll(users);
    }

}
