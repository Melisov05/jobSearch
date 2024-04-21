package com.example.demo.controller;

import com.example.demo.dto.resume.CreateResumeDto;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/candidates")
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public HttpStatus createResume(@RequestBody CreateResumeDto resumeDto){
        log.info("Received DTO: {}", resumeDto);
        resumeService.createResume(resumeDto);
        return HttpStatus.CREATED;
    }
}
