package com.gachon.frimo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.SentimentTagService;
import com.gachon.frimo.web.dto.SentimentTagDto;

@Controller
@RequestMapping(path = "/app/SentTag/")
public class SentimentTagController {
    
    @Autowired
    SentimentTagService sentimentTagService;

    @PostMapping()
    public ResponseEntity<String> addSentTag(@RequestBody SentimentTagDto.AddSentTagRequestDto newSentTag){
        sentimentTagService.addSent(newSentTag);
        return ResponseEntity.status(HttpStatus.OK).body("saved");
    }
}
