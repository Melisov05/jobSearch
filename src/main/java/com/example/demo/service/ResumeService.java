package com.example.demo.service;

import com.example.demo.dto.resume.CreateResumeDto;
import com.example.demo.dto.resume.EditResumeDto;
import com.example.demo.dto.resume.ResumeDto;

import java.util.List;

public interface ResumeService {
        void createResume(CreateResumeDto resumeDto);
        void editResume(EditResumeDto resumeDto);

        Boolean deleteResume(Long id);

        List<ResumeDto> getResumes();

        List<ResumeDto> getResumesByPosition(String position);
}
