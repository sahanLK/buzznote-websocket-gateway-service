package com.buzznote.websocket_gateway.controller;

import com.buzznote.websocket_gateway.config.RabbitConfig;
import com.buzznote.websocket_gateway.dto.ChatMessage;
import com.buzznote.websocket_gateway.service.NotificationWebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

//@RequestMapping("/notification")
//@Component
@Controller
public class NotificationController {

    @MessageMapping("/chat")  // Handles messages sent to /app/chat
    @SendTo("/topic/messages") // Broadcasts to all subscribers of /topic/messages
    public ChatMessage sendMessage(ChatMessage message) throws Exception {
        message.setTimestamp(LocalDateTime.now().toString());
        return message;
    }
}
