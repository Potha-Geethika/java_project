package main.java.com.corbo.service.impl;

import com.example.documentprocessor.dto.AnalyticsResponse;
import com.example.documentprocessor.model.DocumentStatus;
import com.example.documentprocessor.repository.DocumentRepository;
import com.example.documentprocessor.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final DocumentRepository repository;

    @Override
    public Mono<AnalyticsResponse> getSummary() {

        Mono<Long> total = repository.count();

        Mono<Long> processed =
                repository.findByStatus(DocumentStatus.PROCESSED).count();

        Mono<Long> failed =
                repository.findByStatus(DocumentStatus.FAILED).count();

        return Mono.zip(total, processed, failed)
                .map(tuple ->
                        new AnalyticsResponse(
                                tuple.getT1(),
                                tuple.getT2(),
                                tuple.getT3()
                        )
                );
    }
}