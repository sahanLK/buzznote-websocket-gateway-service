package com.buzznote.websocket_gateway.config;

import com.buzznote.websocket_gateway.security.JwtHandshakeInterceptor;
import com.buzznote.websocket_gateway.security.WebSocketAuthChannelInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtHandshakeInterceptor jwtHandshakeInterceptor;

    @Autowired
    private WebSocketAuthChannelInterceptor webSocketAuthChannelInterceptor;

    public WebSocketConfig(JwtHandshakeInterceptor interceptor) {
        this.jwtHandshakeInterceptor = interceptor;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // WebSocket endpoint
                .addInterceptors(jwtHandshakeInterceptor)
                .setAllowedOriginPatterns("*") // allow all origins (change for production)
                .withSockJS(); // fallback to SockJS for unsupported browsers
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue"); // where messages will be sent to
        registry.setApplicationDestinationPrefixes("/app"); // prefix for messages from client
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        System.out.println("Calling");
        registration.interceptors(webSocketAuthChannelInterceptor);
    }
}
