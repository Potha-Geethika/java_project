package com.corbo.service;

import com.corbo.dto.AnalyticsResponse;
import reactor.core.publisher.Mono;

public interface AnalyticsService {

    Mono<AnalyticsResponse> getSummary();
}