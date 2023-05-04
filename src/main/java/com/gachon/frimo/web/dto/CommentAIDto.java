package com.gachon.frimo.web.dto;

import java.time.LocalDateTime;

import com.gachon.frimo.domain.commentAI.CommentAI;
import com.gachon.frimo.domain.diary.Diary;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentAIDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetCommentResponseDto { //DB에서 가져온 댓글을 DTO로 변환하기 위한 DTO
        private Long commentPk;
        private Diary diary; // {diaryPk, authorPk}
        private String commentContent;
        private LocalDateTime commentDate;

        @Builder
        public GetCommentResponseDto(Long commentPk, Diary diary, String commentContent, LocalDateTime commentDate) {
            this.commentPk = commentPk;
            this.diary = diary;
            this.commentContent = commentContent;
            this.commentDate = commentDate;
        }
    }

    public static GetCommentResponseDto toGetCommentResponseDto(CommentAI comment) {
        Long commentPk = comment.getCommentPk();
        Diary diary = comment.getDiary();//{diaryPk, authorPk}
        String commentContent = comment.getCommentContent();
        LocalDateTime commentDate = comment.getCommentDate();

        GetCommentResponseDto getCommentResponseDto = GetCommentResponseDto.builder()
                .commentPk(commentPk)
                .diary(diary)
                .commentDate(commentDate)
                .commentContent(commentContent)
                .build();

        return getCommentResponseDto;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddCommentRequestDto {
        // AddCommentRequestDto 형식으로 넘겨주면 
        private Long diaryPk; // {diaryPk, authorPk}
        private String commentContent;
        private LocalDateTime commentDate;
        private Diary diary;

        @Builder
        public AddCommentRequestDto( Diary diary, String commentContent, LocalDateTime commentDate) {
            this.diary = diary;
            this.commentContent = commentContent;
            this.commentDate = commentDate;
        }
        public CommentAI toEntity() {
            return CommentAI.builder()
                .diary(diary)
                .commentDate(commentDate)
                .commentContent(commentContent)
                .build();
        }

    }
}
