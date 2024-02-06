package com.MrFix30;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//
//@Configuration
//@CrossOrigin(origins="http://localhost:3000") 
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//	 
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//    	 registry.addEndpoint("/ws");
//        registry.addEndpoint("/ws")
//        .setAllowedOriginPatterns("*")
//                .withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/topic"); // Enables a simple in-memory broker for the topic
//        registry.setApplicationDestinationPrefixes("/app"); // Prefix for user requests
//    }
//}