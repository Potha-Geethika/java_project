package com.corbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.corbo.repository")
public class DocumentProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentProcessorApplication.class, args);
    }

}