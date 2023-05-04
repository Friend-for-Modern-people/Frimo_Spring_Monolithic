package com.gachon.frimo.web.dto;

import com.gachon.frimo.domain.diaryInterestTag.DiaryInterestTag;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryInterestTagDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddTagRequestDto {

        private Long diaryPk;
        private String tagContent;
        private Long sentPK; //10,19,28,37,46
        private String category;

        @Builder
        public AddTagRequestDto(Long diaryPk, String tagContent, Long sentPk, String category) {
            this.diaryPk = diaryPk;
            this.tagContent = tagContent;
            this.sentPK = sentPk;
            this.category = category;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetTagResponseDto {
        private String tagContent;
        private int sentLargeId; // 0~5
        private Long diaryPk;
        private Long sentPk;
        private String category;

        @Builder
        public GetTagResponseDto(String tagContent, int sentLargeId, Long diaryPk, Long sentPk, String category) {
            this.tagContent = tagContent;
            this.sentLargeId = sentLargeId;
            this.diaryPk =diaryPk;
            this.sentPk = sentPk;
            this.category = category;
        }
    }

    // Entity -> DTO
    public static GetTagResponseDto toGetTagResponseDto(DiaryInterestTag diaryInterestTag) {

        GetTagResponseDto getTagResponseDto = GetTagResponseDto.builder()
                .tagContent(diaryInterestTag.getTagContent())
                .sentLargeId(diaryInterestTag.getSentimentTag().getSentLargeId())
                .diaryPk(diaryInterestTag.getDiary().getDiaryPk())
                .category(diaryInterestTag.getCategory())
                .sentPk(diaryInterestTag.getSentimentTag().getSentPk())
                .build();

        return getTagResponseDto;

    }
  
}
