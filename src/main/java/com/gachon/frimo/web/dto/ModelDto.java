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
        private String summary;
        private String seg;

        @Builder
        public GetSummaryResponseDto(String summary, String seg) {
            this.summary = summary;
            this.seg = seg;
        }
    }
    
}
