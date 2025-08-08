package com.example.chatapp.service;

import com.example.chatapp.entity.Message;
import com.example.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Messages;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    //enregistrer un message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    //recuperer tout les messages par ordre des
    public List<Messages> getAllMessages() {
        return messageRepository.findAllByOrderByTimestampDesc();
    }

    //recuperer tout les messages recent
    public List<Messages> getRecentMessages() {
        return messageRepository.findTop50ByOrderByTimestamp();
    }
}
