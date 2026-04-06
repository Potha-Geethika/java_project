package com.corbo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "documents")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetadata {

    @Id
    private String id;

    private String fileName;
    private String fileType;
    private long size;
    private String storageUrl;

    private DocumentStatus status;
    private Instant uploadedAt;
    private String uploadedBy;
    
    private int recordCount;
    private Instant processedAt;
    private String errorMessage;
}