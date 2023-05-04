package com.gachon.frimo.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingDto {

    //private String who;
    private String message;
    //private Date time;

    @Builder
    public ChattingDto( String message) {
        this.message = message;
    }
  }
