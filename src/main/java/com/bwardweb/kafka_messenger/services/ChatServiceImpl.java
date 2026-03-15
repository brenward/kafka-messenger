package com.bwardweb.kafka_messenger.services;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.entities.User;
import com.bwardweb.kafka_messenger.mappers.ChatMapper;
import com.bwardweb.kafka_messenger.model.ChatDTO;
import com.bwardweb.kafka_messenger.model.MessageDTO;
import com.bwardweb.kafka_messenger.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
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
        if(user.getChats().isEmpty()){
            return new ArrayList<>();
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

    @Override
    public ChatDTO getChatById(UUID uuid) {
        return chatMapper.mapChatToChatDTO(chatRepository.getReferenceById(uuid));
    }

    @Override
    public long getMaxSequenceOfMessages(UUID chatId) {
        ChatDTO chatDTO = this.getChatById(chatId);

        if (chatDTO == null){
            return -1;
        }

        AtomicLong maxValue = new AtomicLong(-1L);
        OptionalLong maxValueOptional = chatDTO.getMessages().stream().mapToLong(MessageDTO::getSequence).max();

        maxValueOptional.ifPresent(maxValue::set);

        return maxValue.longValue();
    }

    private boolean chatBelongsToUsers(Chat chat, Set<User> users) {
        if(chat.getUsers().size() != users.size()){
            return false;
        }

        return chat.getUsers().containsAll(users);
    }

}
