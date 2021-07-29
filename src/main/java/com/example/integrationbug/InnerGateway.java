package com.example.integrationbug;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

import static com.example.integrationbug.IntegrationConfig.INNER_GATEWAY_INPUT;
import static com.example.integrationbug.IntegrationConfig.INNER_GATEWAY_OUTPUT;

@MessagingGateway(name = "innerGateway")
public interface InnerGateway {
    @Gateway(requestChannel = INNER_GATEWAY_INPUT, replyChannel = INNER_GATEWAY_OUTPUT)
    String someMethod(@Payload String message);
}
