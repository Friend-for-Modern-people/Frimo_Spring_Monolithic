package com.gachon.frimo.web.dto;

import java.util.List;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetUserResponseDto { // DB에서 가져온 댓글을 DTO로 변환하기 위한 DTO
        private Long userPk;
        private String userId;
        private String userNN;
        private List<Diary> diaries;

        @Builder
        public GetUserResponseDto(Long userPk, String userId, String userNN, List<Diary> diaries) {
            this.userPk = userPk;
            this.userId = userId;
            this.userNN = userNN;
            this.diaries = diaries;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegistRequestDto {
        private String userId;
        private String userNN;

        @Builder
        public RegistRequestDto(String userId, String userNN) {
            this.userId = userId;
            this.userNN = userNN;

        }

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .userNN(userNN)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetUserOnlyInfoResponseDto { // DB에서 가져온 댓글을 DTO로 변환하기 위한 DTO
        private Long userPk;
        private String userId;
        private String userNN;

        @Builder
        public GetUserOnlyInfoResponseDto(Long userPk, String userId, String userNN) {
            this.userPk = userPk;
            this.userId = userId;
            this.userNN = userNN;

        }
    }

}
