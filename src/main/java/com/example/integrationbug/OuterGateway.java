package com.example.integrationbug;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

import static com.example.integrationbug.IntegrationConfig.*;

@MessagingGateway(name = "outerGateway")
public interface OuterGateway {
    @Gateway(requestChannel = OUTER_GATEWAY_INPUT, replyChannel = OUTER_GATEWAY_OUTPUT)
    String someMethod(@Payload String message);
}
