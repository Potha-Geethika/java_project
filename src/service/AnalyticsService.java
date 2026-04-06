package com.example.documentprocessor.service;

import com.example.documentprocessor.dto.AnalyticsResponse;
import reactor.core.publisher.Mono;

public interface AnalyticsService {

    Mono<AnalyticsResponse> getSummary();
}