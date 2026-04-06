package main.java.com.corbo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "documents")
public class DocumentMetadata {

    @Id
    private String id;

    private String fileName;
    private String fileType;
    private long size;
    private String storageUrl;

    private String status;
    private String uploadTime;
}