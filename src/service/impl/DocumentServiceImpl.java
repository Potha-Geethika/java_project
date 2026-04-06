package com.example.documentprocessor.service.impl;

import com.example.documentprocessor.dto.DocumentUploadResponse;
import com.example.documentprocessor.kafka.DocumentProducer;
import com.example.documentprocessor.model.DocumentMetadata;
import com.example.documentprocessor.model.DocumentStatus;
import com.example.documentprocessor.repository.DocumentRepository;
import com.example.documentprocessor.service.DocumentService;
import com.example.documentprocessor.service.StorageService;
import com.example.documentprocessor.utils.FileParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final StorageService storageService;
    private final DocumentProducer producer;

    @Override
    public Mono<DocumentUploadResponse> uploadDocument(FilePart file, String user) {

        log.info("Uploading document: {}", file.filename());

        return file.content()
                .reduce((dataBuffer, dataBuffer2) -> dataBuffer)
                .flatMap(buffer -> {

                    byte[] bytes = new byte[buffer.readableByteCount()];
                    buffer.read(bytes);

                    return storageService.upload(file.filename(), bytes)
                            .flatMap(url -> {

                                DocumentMetadata metadata = DocumentMetadata.builder()
                                        .fileName(file.filename())
                                        .fileType("CSV")
                                        .size(bytes.length)
                                        .storageUrl(url)
                                        .uploadedBy(user)
                                        .status(DocumentStatus.UPLOADED)
                                        .uploadedAt(Instant.now())
                                        .build();

                                return repository.save(metadata);
                            });
                })
                .map(saved -> {

                    producer.publish(saved.getId());

                    return new DocumentUploadResponse(
                            saved.getId(),
                            "Document uploaded successfully"
                    );
                });
    }

    @Override
    public Mono<String> processDocument(String documentId) {

        return repository.findById(documentId)
                .flatMap(doc -> {

                    log.info("Processing document {}", doc.getId());

                    doc.setStatus(DocumentStatus.PROCESSING);

                    return repository.save(doc);
                })
                .flatMap(doc -> {

                    try {

                        int records = FileParserUtil.simulateProcessing();

                        doc.setRecordCount(records);
                        doc.setStatus(DocumentStatus.PROCESSED);
                        doc.setProcessedAt(Instant.now());

                        return repository.save(doc);

                    } catch (Exception e) {

                        doc.setStatus(DocumentStatus.FAILED);
                        doc.setErrorMessage(e.getMessage());

                        return repository.save(doc);
                    }

                })
                .map(result -> "Processing completed for " + result.getId());
    }
}