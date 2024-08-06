package com.gbs.auth.listener.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbs.auth.service.RpcService;
import com.gbs.auth.service.UserService;
import com.gbs.common.constants.RabbitMqConstants;
import com.gbs.common.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserListener {
    private final UserService userService;

    private final RpcService rpcService;
    public UserListener(UserService userService, RpcService rpcService) {
        this.userService = userService;
        this.rpcService = rpcService;
    }

    @RabbitListener(queues = RabbitMqConstants.USER_QUEUE,concurrency = "2")
    public void createUser(@Header(value = AmqpHeaders.REPLY_TO, required = false) String senderId,
                           @Header(value = AmqpHeaders.CORRELATION_ID, required = false) String correlationId,
                           UserDTO userDTO) {
        log.info("Received message: {}", userDTO.getPhone());
        UserDTO createdUser = userService.save(userDTO);
        if (senderId != null && correlationId != null) {
            rpcService.sendResponse(senderId, correlationId,(createdUser));
        }
    }
}
