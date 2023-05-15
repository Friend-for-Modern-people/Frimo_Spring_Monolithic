package com.gachon.frimo.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ModelDto {

    @Getter
    @RequiredArgsConstructor
    public static class CreateSummaryRequestDto {
        private String userId;
        private int year;
        private int month;
        private int date;
        private String start_message;

        @Builder
        public CreateSummaryRequestDto(String userId, int year, int month, int date, String start_message) {
            this.userId = userId;
            this.year = year;
            this.month = month;
            this.date = date;
            this.start_message = start_message;
        }

    }

    @Getter
    @RequiredArgsConstructor
    public static class GetSummaryResponseDto {
        private String summaryContent;

        @Builder
        public GetSummaryResponseDto(String summaryContent) {
            this.summaryContent = summaryContent;
        }
    }
    
}
