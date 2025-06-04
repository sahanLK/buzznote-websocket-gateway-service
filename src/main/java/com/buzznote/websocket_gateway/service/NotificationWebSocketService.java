package com.buzznote.websocket_gateway.service;

import com.buzznote.websocket_gateway.dto.SystemMessage;
import com.buzznote.websocket_gateway.dto.SystemNotification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationWebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private SimpUserRegistry simpUserRegistry;

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
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/messages", message);
    }

    public void sendSystemNotification(@Valid SystemNotification notification) {
        System.out.println("Sending Notification");
        simpMessagingTemplate.convertAndSend("/queue/notifications", notification);
    }
}
