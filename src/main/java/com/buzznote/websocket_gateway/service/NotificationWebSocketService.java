package com.buzznote.websocket_gateway.service;

import com.buzznote.websocket_gateway.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationWebSocketService {

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

}
