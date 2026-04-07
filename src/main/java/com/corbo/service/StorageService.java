package com.corbo.service;

import reactor.core.publisher.Mono;

public interface StorageService {

    Mono<String> upload(String fileName, byte[] data);
}