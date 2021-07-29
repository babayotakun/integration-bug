package com.example.integrationbug;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

@Service
public class InnerGatewayTransformer {
    private final InnerGateway innerGateway;

    public InnerGatewayTransformer(InnerGateway innerGateway) {
        this.innerGateway = innerGateway;
    }

    @Transformer
    public String transform(String payload) {
        return innerGateway.someMethod(payload);
    }
}
