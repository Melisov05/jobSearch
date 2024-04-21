package com.example.demo.service;

import com.example.demo.dto.resume.CreateResumeDto;
import com.example.demo.dto.resume.EditResumeDto;
import com.example.demo.dto.resume.ResumeDto;

public interface ResumeService {
        void createResume(CreateResumeDto resumeDto);
        void editResume(EditResumeDto resumeDto);
}
