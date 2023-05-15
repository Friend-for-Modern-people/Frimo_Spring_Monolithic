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
        private String sentimentTag;

        @Builder
        public AddTagRequestDto(Long diaryPk, String tagContent, String sentimentTag) {
            this.diaryPk = diaryPk;
            this.tagContent = tagContent;
            this.sentimentTag = sentimentTag;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetTagResponseDto {
        private String tagContent;
        private Long diaryPk;
        private String sentimentTag;

        @Builder
        public GetTagResponseDto(String tagContent, Long diaryPk, String sentimentTag) {
            this.tagContent = tagContent;
            this.diaryPk =diaryPk;
            this.sentimentTag = sentimentTag;
   
        }
    }

    // Entity -> DTO
    public static GetTagResponseDto toGetTagResponseDto(DiaryInterestTag diaryInterestTag) {

        GetTagResponseDto getTagResponseDto = GetTagResponseDto.builder()
                .tagContent(diaryInterestTag.getTagContent())
                .diaryPk(diaryInterestTag.getDiary().getDiaryPk())
                .sentimentTag(diaryInterestTag.getSentimentTag())
                .build();

        return getTagResponseDto;

    }
  
}
