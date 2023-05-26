package com.gachon.frimo;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.gachon.frimo.web.dto.ModelDto;
import com.gachon.frimo.web.dto.ModelDto.GetSummaryResponseDto;



@FeignClient(
    name = "createDiary", 
    url = "${openfeign.url}"
)
public interface Openfeign {

    @GetMapping(path="/test")
    public String test();


    @PostMapping(path="/model")
    public ModelDto.GetSummaryResponseDto GetSummaryAndSentiment(@RequestBody ModelDto.CreateSummaryRequestDto CreateSummaryRequestDto);

  
}