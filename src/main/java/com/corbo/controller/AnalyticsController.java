package com.corbo.controller;

import com.corbo.dto.AnalyticsResponse;
import com.corbo.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public Mono<AnalyticsResponse> summary() {
        return analyticsService.getSummary();
    }
}