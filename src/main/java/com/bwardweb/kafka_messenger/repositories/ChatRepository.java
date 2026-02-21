package com.bwardweb.kafka_messenger.repositories;

import com.bwardweb.kafka_messenger.entities.Chat;
import com.bwardweb.kafka_messenger.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}
