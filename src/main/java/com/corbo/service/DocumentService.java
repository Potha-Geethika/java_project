package main.java.com.corbo.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface DocumentService {

    Mono<String> uploadDocument(FilePart file);
}