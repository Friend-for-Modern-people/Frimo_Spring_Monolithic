package com.gachon.frimo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gachon.frimo.domain.sentimentTag.SentimentTag;
import com.gachon.frimo.domain.sentimentTag.SentimentTagRepository;
import com.gachon.frimo.web.dto.SentimentTagDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SentimentTagService {
    @Autowired
    SentimentTagRepository sentimentTagRepository;

    @Transactional
    public void addSent(SentimentTagDto.AddSentTagRequestDto tag) {
        SentimentTag  newtag = tag.toEntity();
        sentimentTagRepository.save(newtag);
    }
}
