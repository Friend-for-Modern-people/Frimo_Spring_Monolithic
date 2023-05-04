package com.gachon.frimo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachon.frimo.domain.commentAI.CommentAI;
import com.gachon.frimo.domain.commentAI.CommentAIRepository;
import com.gachon.frimo.domain.diary.Diary;
import com.gachon.frimo.domain.diary.DiaryRepository;
import com.gachon.frimo.web.dto.CommentAIDto;
import com.gachon.frimo.web.dto.CommentAIDto.AddCommentRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentAIService {
    @Autowired
    CommentAIRepository commentAIRepository;
    @Autowired
    DiaryRepository diaryRepository;

    @Transactional //get all **comments** using by 'diaryPk'
    public List<CommentAIDto.GetCommentResponseDto> getCommentInDiary(Long diaryPk) { 
        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        List<CommentAI> comments = commentAIRepository.findAllByDiary(diary);

        return comments.stream().map(CommentAIDto::toGetCommentResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional // post comment
    public void addComment(AddCommentRequestDto addCommentRequestDto){
        Long diaryPk = addCommentRequestDto.getDiaryPk() ;
        LocalDateTime commentDate = addCommentRequestDto.getCommentDate() ;
        String commentContent = addCommentRequestDto.getCommentContent() ;


        Diary diary = diaryRepository.findByDiaryPk(diaryPk);
        AddCommentRequestDto newComment = AddCommentRequestDto.builder()
                .diary(diary)
                .commentDate(commentDate)
                .commentContent(commentContent)
                .build();
        commentAIRepository.save(newComment.toEntity());
    }

}
