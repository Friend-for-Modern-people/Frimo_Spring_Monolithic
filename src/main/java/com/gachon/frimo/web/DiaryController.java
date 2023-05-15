package com.gachon.frimo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.service.DiaryInterestTagService;
import com.gachon.frimo.service.DiaryService;
import com.gachon.frimo.service.UserService;
import com.gachon.frimo.web.dto.DiaryDto;
import com.gachon.frimo.web.dto.ModelDto;
import com.gachon.frimo.web.dto.ModelDto.CreateSummaryRequestDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(path = "/app/diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;
    @Autowired
    UserService userService;
    @Autowired
    DiaryInterestTagService diaryInterestTagService;
    /*
     * 하나의 일기 가져오는 API
     * 
     * @param PathVariable Long diaryPk
     * 
     * @return DiaryDto.GetDiaryResponseDto
     * 
     */
    @GetMapping(value="/{diaryPk}/only1")
    public ResponseEntity<Diary>
    getDiaryEntity(@PathVariable(value="diaryPk") Long diaryPk){
        Diary diary=diaryService.getOneDiary(diaryPk);
        return ResponseEntity.status(HttpStatus.OK).body(diary);
    }
    /*
     * 최신순의 일기를 가져오는 API
     * 
     * @param PathVariable Long userPk
     * 
     * @return List<DiaryDto.GetDiaryResponseDto>
     * 
     */
    @GetMapping(value="/{userPk}")
    public ResponseEntity<List<DiaryDto.GetDiaryResponseDto>>
    getDiaries(@PathVariable(value="userPk") Long userPk){
        List<DiaryDto.GetDiaryResponseDto> diaries=diaryService.getDiaries(userPk);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }
    /*
     * 년도별 일기를 가져오는 API
     * 
     * @param PathVariable Long userPk , PathVariable int month
     * 
     * @return List<DiaryDto.GetDiaryResponseDto>
     * 
     */
    @GetMapping(value="/{userPk}/{year}")
    public ResponseEntity<List<DiaryDto.GetDiaryResponseDto>>
    getDiariesbyYear(@PathVariable(value="userPk") Long userPk, @PathVariable(value="year") int year){
        List<DiaryDto.GetDiaryResponseDto> diaries=diaryService.getDiariesByYear(userPk, year);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }
    /*
     * 월별 일기를 가져오는 API
     * 
     * @param PathVariable Long userPk , PathVariable int year
     * 
     * @return List<DiaryDto.GetDiaryResponseDto>
     * 
     */
    @GetMapping(value="/{userPk}/{year}/{month}")
    public ResponseEntity<List<DiaryDto.GetDiaryResponseDto>>
    getDiariesbyMonth(@PathVariable(value="userPk") Long userPk, @PathVariable(value="year") int year, @PathVariable(value="month") int month){
        List<DiaryDto.GetDiaryResponseDto> diaries=diaryService.getDiariesByMonth(userPk, year, month);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }
    /*
     * 감정별 API
     * 
     * @param PathVariable Long userPk , PathVariable int sent
     * 
     * @return List<DiaryDto.GetDiaryResponseDto>
     * 
     */
    @GetMapping(value="/{userPk}/mainSent/{sent}")
    public ResponseEntity<List<DiaryDto.GetDiaryResponseDto>>
    getDiariesbySent(@PathVariable(value="userPk") Long userPk, @PathVariable(value="sent") int sent){
        List<DiaryDto.GetDiaryResponseDto> diaries=diaryService.getDiariesBySent(userPk, sent);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }
    /*
     * 일기를 등록하는 API (모델)
     * 
     * @param RequestBody DiaryDto.AddDiaryRequestDto
     * 
     * @return  201 CREATED , saved
     */
    @PostMapping(value="")
    public ResponseEntity<String> addDiary(@RequestBody  DiaryDto.AddDiaryRequestDto addDiaryRequestDto) {
        diaryService.addDiary(addDiaryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("saved");
    }
    /*
     * 일기의 mainSent를 수정하는 API (모델)
     * 
     * @param int mainSent, Long diaryPk
     * 
     * @return  patched Diary
     * 
     */
    @PatchMapping(value="/{diaryPk}")
    public ResponseEntity<Diary> patchDiary(@PathVariable(value="diaryPk") Long diaryPk){
        
        return ResponseEntity.status(HttpStatus.OK).body(diaryInterestTagService.getMainSent(diaryPk));
    } 
    /*
     * 작성된 일기의 개수를 전부 가져오는 API
     * 
     * @param PathVariable Long userPk 
     * 
     * @return List<Integer>
     * 
     */
    @GetMapping(value="/{userPk}/cnt")
    public ResponseEntity<Integer>
    getDiariesCnt(@PathVariable(value="userPk") Long userPk){
        
        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiariesCnt(userPk));
    }
   
    /*
     * AI로 일기 작성
     * 
     * @param PathVariable Long userPk, @RequestBody ModelDto.CreateDiaryRequestDto
     * 
     * @return 201 CREATED , saved
     */
    @PostMapping(value="/{userPk}/aiCreate")
    public ResponseEntity<Diary> aiCreateDiary(@PathVariable(value="userPk") Long userPk, @RequestBody CreateSummaryRequestDto createDiaryRequestDto) {
        Diary diary =diaryService.aiCreateDiary(userPk, createDiaryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(diary);
    }
}