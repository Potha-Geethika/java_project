package com.corbo.service.impl;

import com.corbo.dto.AnalyticsResponse;
import com.corbo.model.DocumentStatus;
import com.corbo.repository.DocumentRepository;
import com.corbo.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.corbo.model.DocumentMetadata;


@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

        private final DocumentRepository repository;

        @Override
        public Mono<AnalyticsResponse> getSummary() {

                Mono<Long> total = repository.count();

                Mono<Long> processed = repository.findByStatus(DocumentStatus.PROCESSED).count();

                Mono<Long> failed = repository.findByStatus(DocumentStatus.FAILED).count();

                return Mono.zip(total, processed, failed)
                                .map(tuple -> new AnalyticsResponse(
                                                tuple.getT1(),
                                                tuple.getT2(),
                                                tuple.getT3()));
        }
}