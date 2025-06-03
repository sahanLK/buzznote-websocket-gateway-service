//package com.buzznote.websocket_gateway;
//
//import com.buzznote.websocket_gateway.dto.ChatMessage;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//import java.time.LocalDateTime;
//
//@Controller
//public class MessageController {
//
//    @MessageMapping("/chat")  // Handles messages sent to /app/chatlocalhost:8080
//    @SendTo("/topic/messages") // Broadcasts to all subscribers of /topic/messages
//    public ChatMessage sendMessage(ChatMessage message) throws Exception {
//        message.setTimestamp(LocalDateTime.now().toString());
//        System.out.println("Message Received: " + message);
//        return message;
//    }
//}
//
