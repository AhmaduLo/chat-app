package com.example.chatapp.repository;

import com.example.chatapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thymeleaf.expression.Messages;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Messages> findTop50ByOrderByTimestamp();
    List<Messages> findAllByOrderByTimestampDesc();

}
