package com.gbs.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RpcService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendResponse(String senderId, String correlationId, Object data) {
        this.rabbitTemplate.convertAndSend(senderId, data, message -> {
            MessageProperties properties = message.getMessageProperties();
            System.out.printf("message: %s\n", message);
            properties.setCorrelationId(correlationId);
            return message;
        });
    }
}