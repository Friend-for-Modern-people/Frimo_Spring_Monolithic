package com.gachon.frimo.domain.diaryInterestTag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;
import com.gachon.frimo.domain.diary.Diary;

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

    @Column(name = "sentimentTag")
    private String sentimentTag;

    @Column(name = "tag_content", length = 45) // 해시테그
    private String tagContent;

    @Builder
    public DiaryInterestTag(String tagContent,  Diary diary, String sentimentTag) {
        this.tagContent = tagContent;

        this.diary = diary;
        this.sentimentTag = sentimentTag;
    }

}
