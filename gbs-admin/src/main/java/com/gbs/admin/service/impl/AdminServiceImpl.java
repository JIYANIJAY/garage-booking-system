package com.gbs.admin.service.impl;

import com.gbs.admin.service.AdminService;
import com.gbs.common.constants.RabbitMqConstants;
import com.gbs.common.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    private final RabbitTemplate rabbitTemplate;

    public AdminServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Sending message to user service: {}", userDTO.getPhone());
        try {
            Message message = MessageBuilder.withBody(userDTO.toString().getBytes())
                    .setContentType("application/json")
                    .build();
            return (UserDTO) rabbitTemplate.convertSendAndReceive(RabbitMqConstants.DIRECT_EXCHANGE, RabbitMqConstants.USER_ROUTING_KEY, userDTO);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
        }
        return null;
    }
}
