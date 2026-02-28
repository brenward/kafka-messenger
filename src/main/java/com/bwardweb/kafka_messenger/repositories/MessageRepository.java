package com.bwardweb.kafka_messenger.repositories;

import com.bwardweb.kafka_messenger.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    Page<Message> findByChatId(UUID chatId, Pageable pageable);

    @Query("select m from Message m where chat.id = ?1 and sequence >= ?2")
    Page<Message> findByChatIdAndSequence(UUID chatId, Long sequence, Pageable pageable);

    @Query("select max(m.sequence) from Message m where chat.id = ?1")
    Long findMaxSequenceByChatId(UUID chatId);
}
