package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.service.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
}
