package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachon.frimo.Openfeign;
import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diary.DiaryRepository;
import com.gachon.frimo.domain.user.UserRepository;
import com.gachon.frimo.web.dto.DiaryDto;
import com.gachon.frimo.web.dto.ModelDto;
import com.gachon.frimo.web.dto.DiaryDto.GetDiaryResponseDto;
import com.gachon.frimo.web.dto.ModelDto.CreateSummaryRequestDto;

import lombok.RequiredArgsConstructor;

import com.gachon.frimo.domain.user.User;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Openfeign openfeign;

    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiaries(Long userPk) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .sorted(Comparator.comparing(Diary::getDateCreated).reversed())
                .map(DiaryDto::toGetDiaryResponseDto)

                .collect(Collectors.toList());
    }

    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiariesByYear(Long userPk, int year) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .filter(diary -> (diary.getDateCreatedYear() == year))
                .sorted(Comparator.comparing(Diary::getDateCreated).reversed())
                .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());

    }

    @Transactional
    public List<DiaryDto.GetDiaryResponseDto> getDiariesByMonth(Long userPk, int year, int month) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .filter(diary -> (diary.getDateCreatedYear() == year && diary.getDateCreatedMonth() == month))
                .sorted(Comparator.comparing(Diary::getDateCreated).reversed())
                .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());
    }

    // 일기 하나
    @Transactional
    public Diary getOneDiary(Long diaryPk) {
        return diaryRepository.findByDiaryPk(diaryPk);
        
    }

    @Transactional
    public Diary addDiary(DiaryDto.AddDiaryRequestDto addDiaryRequestDto) {
        String diaryContent = addDiaryRequestDto.getDiaryContent();
        Long userPk = addDiaryRequestDto.getUserPk(); // userPk로 service단에서 user찾기
        LocalDateTime dateCreated = addDiaryRequestDto.getDateCreated();
        int dateCreatedYear = dateCreated.getYear();
        int dateCreatedMonth = dateCreated.getMonthValue();
        int mainSent = 7; // initial value
        String imagePath = "";
        User user = userRepository.findByUserPk(userPk);
        // System.out.print("userid : "+user.getUserId());
        Diary newDiary = Diary.builder()
                .diaryContent(diaryContent)
                .dateCreated(dateCreated)
                .author(user)
                .dateCreatedMonth(dateCreatedMonth)
                .dateCreatedYear(dateCreatedYear)
                .mainSent(mainSent)
                .imagePath(imagePath)
                .build();
       return diaryRepository.save(newDiary);
    }

    @Transactional
    public int getDiariesCnt(Long userPk) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.size();
    }

    @Transactional
    public List<GetDiaryResponseDto> getDiariesBySent(Long userPk, int sent) {
        User user = userRepository.findByUserPk(userPk);
        List<Diary> diaries = diaryRepository.findAllByAuthor(user);

        return diaries.stream()
                .filter(diary -> (diary.getMainSent() == sent))
                .map(DiaryDto::toGetDiaryResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Diary aiCreateDiary(Long userPk, CreateSummaryRequestDto createDiaryRequestDto) {
        User user = userRepository.findByUserPk(userPk);
        String result = openfeign.GetSummaryAndSentiment(createDiaryRequestDto);

        LocalDateTime dateCreated = LocalDateTime.now();
        Diary newDiary = Diary.builder()
                .diaryContent(result)
                .dateCreated(dateCreated)
                .author(user)
                .dateCreatedMonth(dateCreated.getMonthValue())
                .dateCreatedYear(dateCreated.getYear())
                .mainSent(7)
                .build();
               

        return diaryRepository.save(newDiary);
    }

}
