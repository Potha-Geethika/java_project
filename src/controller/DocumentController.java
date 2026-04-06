package com.example.documentprocessor.controller;

import com.example.documentprocessor.dto.DocumentUploadResponse;
import com.example.documentprocessor.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @PostMapping("/upload")
    public Mono<DocumentUploadResponse> uploadDocument(
            @RequestPart("file") FilePart file,
            @RequestHeader("user") String user
    ) {

        return service.uploadDocument(file, user);
    }

    @PostMapping("/process/{id}")
    public Mono<String> processDocument(@PathVariable String id) {

        return service.processDocument(id);
    }

}