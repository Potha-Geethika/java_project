package com.example.documentprocessor.kafka;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class DocumentProducer {

    private final StreamBridge streamBridge;

    public DocumentProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publish(String documentId) {
        streamBridge.send("document-out-0", documentId);
    }
}