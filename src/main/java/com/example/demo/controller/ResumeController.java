package com.example.demo.controller;

import com.example.demo.dto.resume.CreateResumeDto;
import com.example.demo.dto.resume.EditResumeDto;
import com.example.demo.dto.resume.ResumeDto;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public HttpStatus createResume(@RequestBody CreateResumeDto resumeDto){
        log.info("Received DTO: {}", resumeDto);
        resumeService.createResume(resumeDto);
        return HttpStatus.CREATED;
    }

    @PutMapping
    public HttpStatus editResume(@RequestBody EditResumeDto resumeDto){
        log.info("Received DTO for change: {}", resumeDto);
        resumeService.editResume(resumeDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteResume(@PathVariable Long id){
        if(resumeService.deleteResume(id)){
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getResumes(){
        return ResponseEntity.ok(resumeService.getResumes());
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<List<ResumeDto>> getResumesByPosition(@PathVariable String position){
        return ResponseEntity.ok(resumeService.getResumesByPosition(position));
    }
}
