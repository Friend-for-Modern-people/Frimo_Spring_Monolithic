package com.gachon.frimo.domain.commentAI;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;
import com.gachon.frimo.domain.diary.Diary;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
@Table(name="comment")
public class CommentAI extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_pk")
    private Long commentPk;

    @Column(name ="comment_content", length =256)
    private String commentContent;

    @Column(name ="comment_date")
    private LocalDateTime commentDate;

    @Column(name="diary")
    private Diary diary;

    @Builder
    public CommentAI (String commentContent, LocalDateTime commentDate, Diary diary){
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.diary = diary;
    }
}