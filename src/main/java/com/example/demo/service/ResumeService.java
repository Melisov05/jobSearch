package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import org.springframework.stereotype.Service;

public interface ResumeService {
        void createResume(ResumeDto resumeDto);
}
