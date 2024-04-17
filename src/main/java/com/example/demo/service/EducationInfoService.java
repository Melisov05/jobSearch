package com.example.demo.service;

import com.example.demo.dto.CreateEducationInfoDto;

public interface EducationInfoService {
    void createEducationInfo(CreateEducationInfoDto education, Long id);
}
