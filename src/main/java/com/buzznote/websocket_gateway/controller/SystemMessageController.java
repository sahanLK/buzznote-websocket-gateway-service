package com.buzznote.websocket_gateway.controller;

import com.buzznote.websocket_gateway.config.RabbitConfig;
import com.buzznote.websocket_gateway.dto.SystemMessage;
import com.buzznote.websocket_gateway.service.NotificationWebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SystemMessageController {
    @Autowired
    private NotificationWebSocketService notificationWebSocketService;

    @RabbitListener(queues = RabbitConfig.SYSTEM_NOTIFICATION_QUEUE)
    public void listenTemp(SystemMessage message) throws Exception {
//        String to = message.get("body");
//        notificationWebSocketService.broadcast(message);
        System.out.println("Message Received: " + message);
    }
}
