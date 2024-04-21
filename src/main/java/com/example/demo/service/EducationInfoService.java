package com.example.demo.service;

import com.example.demo.dto.educationInfo.CreateEducationInfoDto;

public interface EducationInfoService {
    void createEducationInfo(CreateEducationInfoDto education, Long id);

    void editEducationInfo(CreateEducationInfoDto education, Long id);
}
