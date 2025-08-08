package com.example.chatapp.config;

import com.example.chatapp.entity.Message;
import com.example.chatapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final UserService userService;
    // sert à envoyer des messages à des abonnés via /topic/… → équivalent WebSocket du RestTemplate
    private final SimpMessageSendingOperations messageTemplate;

    //il est appelée automatiquement par Spring quand un client WebSocket se connecte à mon application
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        //le headerAccessor va me permettre de recup le message et aux informations du client
        var headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        //récupères le nom d’utilisateur qui a été stocké en session lors de la connexion WebSocket
        var username = headerAccessor.getSessionAttributes().get("username").toString();

        if (Objects.nonNull(username)) {
            var user = userService.findByUsername(username);
            userService.setUserOffline(user);

            // je crées un objet Message de type LEAVE pour signaler aux autres clients que ce user a quitté le chat.
            //j’envoies à tous les clients abonnés à /topic/leav
            var leaveMessage = new Message();
            leaveMessage.setType(Message.MessageType.LEAVE);
            leaveMessage.setSender(user);

            messageTemplate.convertAndSend("/topic/leave", leaveMessage);
        }


    }
}
