package com.example.documentprocessor.controller;

import com.example.documentprocessor.dto.AnalyticsResponse;
import com.example.documentprocessor.service.AnalyticsService;
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