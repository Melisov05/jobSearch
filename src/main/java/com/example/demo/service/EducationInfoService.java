package com.example.demo.service;

import com.example.demo.dto.educationInfo.CreateEducationInfoDto;
import com.example.demo.dto.educationInfo.EditEducationInfoDto;

public interface EducationInfoService {
    void createEducationInfo(CreateEducationInfoDto education, Long id);

    void editEducationInfo(EditEducationInfoDto education, Long id);
}
