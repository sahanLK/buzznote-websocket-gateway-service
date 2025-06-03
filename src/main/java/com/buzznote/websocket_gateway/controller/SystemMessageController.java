package com.buzznote.websocket_gateway.controller;

import com.buzznote.websocket_gateway.config.RabbitConfig;
import com.buzznote.websocket_gateway.dto.ChatMessage;
import com.buzznote.websocket_gateway.dto.SystemMessage;
import com.buzznote.websocket_gateway.service.NotificationWebSocketService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Component
public class SystemMessageController {
    @Autowired
    private NotificationWebSocketService notificationWebSocketService;



    @RabbitListener(queues = RabbitConfig.SYSTEM_NOTIFICATION_QUEUE)
    public void sendSystemMessage(@Valid SystemMessage message) throws Exception {
//        String to = message.get("body");
//        System.out.println("PRINCIPAL: " + principal.getName());
        System.out.println("Message Received: " + message);
        notificationWebSocketService.sendSystemMessage(message);

    }
}
