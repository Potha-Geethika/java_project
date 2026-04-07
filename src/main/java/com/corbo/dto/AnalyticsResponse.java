package com.corbo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyticsResponse {

    private long totalDocuments;
    private long processedDocuments;
    private long failedDocuments;
}