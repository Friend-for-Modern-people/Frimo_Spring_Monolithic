package com.gachon.frimo.domain.diaryInterestTag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;
import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.sentimentTag.SentimentTag;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diary_interest_tag")
public class DiaryInterestTag extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diary")
    private Diary diary;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "sentimentTag")
    private SentimentTag sentimentTag;

    @Column(name = "tag_content", length = 45) // 해시테그
    private String tagContent;

    @Column(name = "category", length=16)
    private String category;

    @Builder
    public DiaryInterestTag(String tagContent, String category, Diary diary, SentimentTag sentimentTag) {
        this.tagContent = tagContent;
        this.category = category;
        this.diary = diary;
        this.sentimentTag = sentimentTag;
    }

}
