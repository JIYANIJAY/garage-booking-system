package com.gbs.admin.config.rabbit;

import com.gbs.common.constants.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class UserWeb {

    // Define Queue
    @Bean
    Queue saveUser() {
        return new Queue(RabbitMqConstants.USER_QUEUE, true);
    }

    // Define Exchange
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(RabbitMqConstants.DIRECT_EXCHANGE);
    }

    // Queue binding
    @Bean
    Binding saveUserBinding(Queue saveUser, DirectExchange directExchange) {
        return BindingBuilder.bind(saveUser).to(directExchange).with(RabbitMqConstants.USER_ROUTING_KEY);
    }

}
