package com.corbo.service;

import com.corbo.dto.DocumentUploadResponse;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface DocumentService {

    Mono<DocumentUploadResponse> uploadDocument(FilePart file, String user);
    
    Mono<String> processDocument(String documentId);
}