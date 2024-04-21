package com.example.demo.service;

import com.example.demo.dto.workExperienceInfo.CreateWorkExperienceInfoDto;

public interface WorkExperienceInfoService {
    void createWorkExperienceInfo(CreateWorkExperienceInfoDto dto, Long id);

    void editWorkExperienceInfo(CreateWorkExperienceInfoDto dto, Long id);
}
