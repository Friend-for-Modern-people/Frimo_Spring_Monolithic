package com.gachon.frimo.web.dto;

import com.gachon.frimo.domain.sentimentTag.SentimentTag;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SentimentTagDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddSentTagRequestDto {
        private int sentLargeId;
        private String sentSmallId;

        @Builder
        public AddSentTagRequestDto(int sentLargeId, String sentSmallId) {
            this.sentLargeId = sentLargeId;
            this.sentSmallId = sentSmallId;
        }
        public SentimentTag toEntity() {
            return SentimentTag.builder()
                .sentLargeId(sentLargeId)
                .sentSmallId(sentSmallId)
                .build();
        }

    }
    
}
