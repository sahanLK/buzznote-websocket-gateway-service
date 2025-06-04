package com.buzznote.websocket_gateway.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String SYSTEM_MESSAGE_QUEUE = "system.message.queue";
    public static final String SYSTEM_MESSAGE_EXCHANGE = "system.message.exchange";
    public static final String SYSTEM_MESSAGE_ROUTING_KEY = "system.message";

    public static final String SYSTEM_NOTIFICATION_QUEUE = "system.notification.queue";
    public static final String SYSTEM_NOTIFICATION_EXCHANGE = "system.notification.exchange";
    public static final String SYSTEM_NOTIFICATION_ROUTING_KEY = "system.notification";

    @Bean
    public Queue systemMessageQueue() {
        return new Queue(SYSTEM_MESSAGE_QUEUE);
    }

    @Bean
    public DirectExchange systemMessageExchange() {
        return new DirectExchange(SYSTEM_MESSAGE_EXCHANGE);
    }

    @Bean
    public Binding bindingSystemMessageQueue() {
        return BindingBuilder.bind(systemMessageQueue()).to(systemMessageExchange()).with(SYSTEM_MESSAGE_ROUTING_KEY);
    }

    @Bean
    public Queue systemNotificationQueue() {
        return new Queue(SYSTEM_NOTIFICATION_QUEUE);
    }

    @Bean
    public DirectExchange systemNotificationExchange() {
        return new DirectExchange(SYSTEM_NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Binding bindingSystemNotificationQueue() {
        return BindingBuilder.bind(systemNotificationQueue()).to(systemNotificationExchange()).with(SYSTEM_NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

}
