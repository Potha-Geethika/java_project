package com.example.documentprocessor.repository;

import com.example.documentprocessor.model.DocumentMetadata;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DocumentRepository extends ReactiveMongoRepository<DocumentMetadata, String> {
}