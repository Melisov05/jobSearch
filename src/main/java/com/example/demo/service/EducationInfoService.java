package com.example.demo.service;

import com.example.demo.dto.educationInfo.CreateEducationInfoDto;
import com.example.demo.dto.educationInfo.EditEducationInfoDto;
import com.example.demo.dto.educationInfo.EducationInfoDto;
import com.example.demo.dto.workExperienceInfo.WorkExperienceInfoDto;
import com.example.demo.model.EducationInfo;

import java.util.List;

public interface EducationInfoService {
    void createEducationInfo(CreateEducationInfoDto education, Long id);

    void editEducationInfo(EditEducationInfoDto education, Long id);

    EducationInfoDto toDto(EducationInfo info);

    List<EducationInfoDto> getEducationInfoByResumeId(Long resumeId);
}
