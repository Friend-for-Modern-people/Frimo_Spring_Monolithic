package com.gachon.frimo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.DiaryInterestTagService;
import com.gachon.frimo.web.dto.DiaryInterestTagDto;

@Controller
@RequestMapping(path = "/app/tag")
public class DiaryInterestTagController {
    @Autowired
    DiaryInterestTagService diaryInterestTagService;
    /*
     * 모델의 아웃풋으로 나오는 태그를 저장하는 API
     * 
     * @param PathVariable Long userPk , 
     * 
     * @return List<DiaryDto.>
     * 
     */

    /*
     * 유저가 직접 추가하는 테그를 저장하는 API
     * 
     * @param RequestBody DiaryDto.AddDiaryRequestDto , Path Long userPk, Path Long diaryPk
     * 
     * @return 201 CREATED , saved
     */
    @PostMapping(value = "")
    public ResponseEntity<String> addTag(@RequestBody DiaryInterestTagDto.AddTagRequestDto addTagRequestDto) {
        diaryInterestTagService.addTag(addTagRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("saved");
    }

    /*
     * 일기에 속한 모든 테그를 가져오는 API
     * 
     * @param Path Long userPk, Path Long diaryPk
     * 
     * @return List<DiaryInterestTagDto.GetTagResponseDto>
     */
    @GetMapping(path = "/{diaryPk}")
    public ResponseEntity<List<DiaryInterestTagDto.GetTagResponseDto>> getTags(@PathVariable(value = "diaryPk") Long diaryPk) {
        List<DiaryInterestTagDto.GetTagResponseDto> gettags = diaryInterestTagService.getTags(diaryPk);

        return ResponseEntity.status(HttpStatus.OK).body(gettags);
    }

    /*
     * 일기에 속한 4개 테그를 가져오는 API
     * 
     * @param RequestBody DiaryDto.AddDiaryRequestDto
     * 
     * @return List<DiaryInterestTagDto.GetTagResponseDto>
     */
    @GetMapping(path = "/{diaryPk}/only4")
    public ResponseEntity<List<DiaryInterestTagDto.GetTagResponseDto>> get4Tags(@PathVariable(value = "diaryPk") Long diaryPk) {
        List<DiaryInterestTagDto.GetTagResponseDto> gettags = diaryInterestTagService.getTagsOnly4(diaryPk);

        return ResponseEntity.status(HttpStatus.OK).body(gettags);
    }
}
