package com.example.integrationbug;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executors;

@Configuration
@EnableIntegration
@IntegrationComponentScan(basePackages = "com.example.integrationbug")
public class IntegrationConfig {
    public static final String INNER_GATEWAY_INPUT = "gatewayInput";
    public static final String OUTER_GATEWAY_INPUT = "outerGatewayInput";
    public static final String INNER_GATEWAY_OUTPUT = "gatewayOutput";
    public static final String OUTER_GATEWAY_OUTPUT = "outerGatewayOutput";

    @Bean
    public MessageChannel gatewayInput() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public MessageChannel outerGatewayInput() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public MessageChannel gatewayOutput() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public MessageChannel outerGatewayOutput() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public IntegrationFlow outerFlow(InnerGatewayTransformer transformer) {
        return IntegrationFlows.from(OUTER_GATEWAY_INPUT)
            .transform(transformer)
            .channel(OUTER_GATEWAY_OUTPUT)
            .get();
    }

    @Bean
    public IntegrationFlow innerFlow(SlowTransformer transformer) {
        return IntegrationFlows.from(INNER_GATEWAY_INPUT)
            .transform(transformer)
            .channel(INNER_GATEWAY_OUTPUT)
            .get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    @Primary
    public PollerMetadata poller() {
        return Pollers.fixedRate(500)
            .taskExecutor(Executors.newFixedThreadPool(4)).get();
    }
}

