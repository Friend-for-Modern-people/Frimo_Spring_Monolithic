package com.gachon.frimo.domain.diary;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;
import com.gachon.frimo.domain.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diary")
public class Diary extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_pk")
    private Long diaryPk;

    @Column(name = "diary_title", length = 45)
    private String diaryTitle;

    @Column(name = "diary_content", length = 500)
    private String diaryContent;

    @Column(name = "image_path", length = 100)
    private String imagePath;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_created_year")
    private int dateCreatedYear;

    @Column(name = "date_created_month")
    private int dateCreatedMonth;

    @Setter
    @Column(name = "main_sent") // 제일 빈도 수 많은 감정을 뽑기 -> 별도의 함수 만들기
    private int mainSent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author; // userPk

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "diary")
    // private List<DiaryInterestTag> tags;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "diary")
    // private List<CommentAI> comments;

    @Builder
    public Diary(String diaryTitle, String diaryContent, String imagePath, User author, LocalDateTime dateCreated,
            int mainSent, int dateCreatedYear, int dateCreatedMonth) {
        this.diaryContent = diaryContent;
        this.diaryTitle = diaryTitle;
        this.imagePath = imagePath;
        this.author = author;
        this.dateCreated = dateCreated;
        this.mainSent = mainSent;
        this.dateCreatedYear = dateCreatedYear;
        this.dateCreatedMonth = dateCreatedMonth;
    }
}
