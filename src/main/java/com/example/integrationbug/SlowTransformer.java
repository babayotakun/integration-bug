package com.example.integrationbug;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

@Service
public class SlowTransformer {
    @Transformer
    public String transform(String payload) {
        System.out.println("Start transforming");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Transformed");
        return payload;
    }
}
