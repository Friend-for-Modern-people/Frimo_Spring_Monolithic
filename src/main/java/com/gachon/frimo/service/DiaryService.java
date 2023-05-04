package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diary.DiaryRepository;
import com.gachon.frimo.domain.user.UserRepository;
import com.gachon.frimo.web.dto.DiaryDto;
import com.gachon.frimo.web.dto.DiaryDto.GetDiaryResponseDto;

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
    public DiaryDto.GetDiaryResponseDto getOneDiary(Long diaryPk) {
        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        return DiaryDto.toGetDiaryResponseDto(diary);
    }

    // 일기추가
    // TODO : tag를 먼저 추가하고 main_Sent를 구한 다음에 일기를 작성하는 방식 X
    // 위의 방식대로 하려면 diaryPK가 없어서 tag를 입력받지 못함.
    // 따라서 결국 patch를 해야한다는 소리.
    // patch가 불리는 타이밍은?

    // 혹은 일기와 테그를 한번에 받을 수 있다면 pk먼저 저장하고 tag저장하고 mainSent를 저장하는 방식으로 사용해볼수 있을듯
    // (PostMapping한번에)

    // 모델에서 입력을 받을 때 diary 생성, tag저장, diary patch

    @Transactional
    public void addDiary(DiaryDto.AddDiaryRequestDto addDiaryRequestDto) {
        String diaryTitle = addDiaryRequestDto.getDiaryTitle();
        String diaryContent = addDiaryRequestDto.getDiaryContent();
        Long userPk = addDiaryRequestDto.getUserPk(); // userPk로 service단에서 user찾기
        LocalDateTime dateCreated = addDiaryRequestDto.getDateCreated();
        int dateCreatedYear = dateCreated.getYear();
        int dateCreatedMonth = dateCreated.getMonthValue();
        int mainSent = 7; // initial value
        String imagePath = "";
        // TODO : tag에서 mainSent 계산하는 함수 필요
        System.out.print("userid : " + userPk);
        User user = userRepository.findByUserPk(userPk);
        // System.out.print("userid : "+user.getUserId());
        Diary newDiary = Diary.builder()
                .diaryContent(diaryContent)
                .diaryTitle(diaryTitle)
                .dateCreated(dateCreated)
                .author(user)
                .dateCreatedMonth(dateCreatedMonth)
                .dateCreatedYear(dateCreatedYear)
                .mainSent(mainSent)
                .imagePath(imagePath)
                .build();
        diaryRepository.save(newDiary);
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

}
