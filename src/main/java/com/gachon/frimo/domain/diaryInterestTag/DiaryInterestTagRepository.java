package com.gachon.frimo.domain.diaryInterestTag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gachon.frimo.domain.diary.Diary;


@Repository
public interface DiaryInterestTagRepository extends JpaRepository<DiaryInterestTag, Long>{
    public List<DiaryInterestTag> findAllByTagContent(String tagContent);
    public List<DiaryInterestTag> findAllByDiary(Diary diary); 
    
    
}
