package com.example.documentprocessor.service.impl;

import com.example.documentprocessor.service.StorageService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StorageServiceImpl implements StorageService {

    @Override
    public Mono<String> upload(String fileName, byte[] data) {

        // Placeholder for Azure Blob upload
        String url = "https://azure.storage/" + fileName;

        return Mono.just(url);
    }
}