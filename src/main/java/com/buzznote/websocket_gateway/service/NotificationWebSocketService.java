package com.buzznote.websocket_gateway.service;

import com.buzznote.websocket_gateway.dto.ChatMessage;
import com.buzznote.websocket_gateway.dto.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class NotificationWebSocketService {

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public NotificationWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.simpMessagingTemplate = messagingTemplate;
    }

    public void broadcast(String message) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("content", "Hello");
        simpMessagingTemplate.convertAndSend("/topic/messages", msg);
    }

    public void sendSystemMessage(SystemMessage message) {
        System.out.println("Sending Message to: " + message.getTo());

        Set<SimpUser> users = simpUserRegistry.getUsers();
        for (SimpUser user : users) {
            System.out.println("User: " + user.getName());
            user.getSessions().forEach(session -> {
                System.out.println(" - Session ID: " + session.getId());
            });
        }

        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/messages", message);
    }

}
