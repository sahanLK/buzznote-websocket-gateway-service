package com.buzznote.websocket_gateway.controller;

import com.buzznote.websocket_gateway.config.RabbitConfig;
import com.buzznote.websocket_gateway.dto.SystemMessage;
import com.buzznote.websocket_gateway.dto.SystemNotification;
import com.buzznote.websocket_gateway.service.JwtService;
import com.buzznote.websocket_gateway.service.NotificationWebSocketService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private NotificationWebSocketService notificationWebSocketService;

    @RabbitListener(queues = RabbitConfig.SYSTEM_MESSAGE_QUEUE)
    public void systemMessageListener(@Valid SystemMessage message) throws Exception {
        notificationWebSocketService.sendSystemMessage(message);
    }

    @RabbitListener(queues = RabbitConfig.SYSTEM_NOTIFICATION_QUEUE)
    public void systemNotificationListener(@Valid SystemNotification notification) throws Exception {
        notificationWebSocketService.sendSystemNotification(notification);
    }

}
