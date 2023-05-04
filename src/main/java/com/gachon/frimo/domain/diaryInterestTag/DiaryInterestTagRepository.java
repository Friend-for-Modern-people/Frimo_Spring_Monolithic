package com.gachon.frimo.domain.diaryInterestTag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.sentimentTag.SentimentTag;


@Repository
public interface DiaryInterestTagRepository extends JpaRepository<DiaryInterestTag, Long>{
    public List<DiaryInterestTag> findAllByCategory(String category);
    public List<DiaryInterestTag> findAllByTagContent(String tagContent);
    public List<DiaryInterestTag> findAllBySentimentTag(SentimentTag sentimentTag);
    public List<DiaryInterestTag> findAllByDiary(Diary diary); 
    
    
}
