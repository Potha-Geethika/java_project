package com.corbo.repository;

import com.corbo.model.DocumentMetadata;
import com.corbo.model.DocumentStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DocumentRepository extends ReactiveMongoRepository<DocumentMetadata, String> {
    Flux<DocumentMetadata> findByStatus(DocumentStatus status);
}