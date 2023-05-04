package com.gachon.frimo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diary.DiaryRepository;
import com.gachon.frimo.domain.diaryInterestTag.DiaryInterestTag;
import com.gachon.frimo.domain.diaryInterestTag.DiaryInterestTagRepository;
import com.gachon.frimo.domain.sentimentTag.SentimentTag;
import com.gachon.frimo.domain.sentimentTag.SentimentTagRepository;
import com.gachon.frimo.web.dto.DiaryDto;
import com.gachon.frimo.web.dto.DiaryInterestTagDto;
import com.gachon.frimo.web.dto.DiaryInterestTagDto.GetTagResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryInterestTagService {
    @Autowired
    DiaryInterestTagRepository diaryInterestTagRepository;
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    SentimentTagRepository sentimentTagRepository;

    @Transactional
    // 해당 일기의 태그에서 감정만 뽑아 계산하기, 가장 많은 감정을 반환 
    public Diary getMainSent(Long diaryPk) {

        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        List<DiaryInterestTag> tags = diaryInterestTagRepository.findAllByDiary(diary);

        List<DiaryInterestTag> angerlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 0))
                .collect(Collectors.toList());
        List<DiaryInterestTag> sadlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 1))
                .collect(Collectors.toList());
        List<DiaryInterestTag> anxilist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 2))
                .collect(Collectors.toList());
        List<DiaryInterestTag> hurtlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 3))
                .collect(Collectors.toList());
        List<DiaryInterestTag> embarlist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 4))
                .collect(Collectors.toList());
        List<DiaryInterestTag> haplist = tags.stream()
                .filter(t -> (t.getSentimentTag().getSentLargeId() == 5))
                .collect(Collectors.toList());

        int anger = angerlist.size(); // 0
        int sadness = sadlist.size(); // 1
        int anxiety = anxilist.size(); // 2
        int hurts = hurtlist.size(); // 3
        int embarrassed = embarlist.size(); // 4
        int happiness = haplist.size(); // 5

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, anger);
        map.put(1, sadness);
        map.put(2, anxiety);
        map.put(3, hurts);
        map.put(4, embarrassed);
        map.put(5, happiness );
        Integer mainSent = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();

        if(mainSent != 7){
            diary.setMainSent(mainSent);
        }
       
        return diaryRepository.save(diary);

    }

    // 테그를 추가 ( 모델의 아웃풋으로 나오는 태그도 저장할 수 있을까..?)
    @Transactional
    public void addTag(DiaryInterestTagDto.AddTagRequestDto tag) {
        Diary diary = diaryRepository.findByDiaryPk(tag.getDiaryPk());
        SentimentTag sentimentTag = sentimentTagRepository.findBySentPk(tag.getSentPK());

        DiaryInterestTag newTag = DiaryInterestTag.builder()
                .tagContent(tag.getTagContent())
                .category(tag.getCategory())
                .diary(diary)
                .sentimentTag(sentimentTag)
                .build();
        diaryInterestTagRepository.save(newTag);
    }

 // 일기에 테그 중 4개만 가져오기
 @Transactional
 public List<DiaryInterestTagDto.GetTagResponseDto> getTags (Long diaryPk){
     Diary diary = diaryRepository.findByDiaryPk(diaryPk);
     List<DiaryInterestTag> tags = diaryInterestTagRepository.findAllByDiary(diary);

     return  tags.stream()
     .map(DiaryInterestTagDto::toGetTagResponseDto)
             .collect(Collectors.toList());

 }

    // 일기에 테그 중 4개만 가져오기
    @Transactional
    public List<DiaryInterestTagDto.GetTagResponseDto> getTagsOnly4 (Long diaryPk){
        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        List<DiaryInterestTag> tags = diaryInterestTagRepository.findAllByDiary(diary);

        return  tags.stream()
        .limit(4)
        .map(DiaryInterestTagDto::toGetTagResponseDto)
                .collect(Collectors.toList());

    }

}
