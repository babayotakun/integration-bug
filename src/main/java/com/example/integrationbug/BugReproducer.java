package com.example.integrationbug;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BugReproducer {
    private final OuterGateway gateway;

    public BugReproducer(OuterGateway gateway) {
        this.gateway = gateway;
    }

    @EventListener
    public void test(ContextRefreshedEvent event) {
        System.out.println("Test start");
        System.out.println(gateway.someMethod("Test message"));
        System.out.println("Test stop");
    }
}
