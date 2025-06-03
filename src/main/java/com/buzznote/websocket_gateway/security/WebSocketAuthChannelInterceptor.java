package com.buzznote.websocket_gateway.security;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class WebSocketAuthChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        System.out.println("User set");

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Principal user = (Principal) accessor.getSessionAttributes().get("user");
            if (user != null) {
                System.out.println("User set");
                accessor.setUser(user); // ✅ Crucial line
                System.out.println("WebSocket user: " + user.getName());
            }
        }

        return message;
    }
}
