package com.example.documentprocessor.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DocumentConsumer {

    @Bean
    public Consumer<String> documentListener() {

        return message -> {
            System.out.println("Processing document: " + message);
        };
    }
}