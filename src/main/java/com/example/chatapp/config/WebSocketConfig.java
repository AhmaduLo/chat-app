package com.example.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
//le @EnableWebSocketMessageBroker de definir des canaux qu'on pourrat envoyer des messages different durant des chanel qu'on aura definit
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //sert à configurer les canaux de communication entre les clients et le serveur via WebSocket + STOMP.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //definir un endpoint qu'on va utiliser pour ce connecter a mon server webskot
        registry.addEndpoint("/ws").withSockJS();
    }

}
