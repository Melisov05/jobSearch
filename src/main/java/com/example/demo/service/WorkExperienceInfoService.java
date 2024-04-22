package com.example.demo.service;

import com.example.demo.dto.workExperienceInfo.CreateWorkExperienceInfoDto;
import com.example.demo.dto.workExperienceInfo.EditWorkExperienceDto;
import com.example.demo.dto.workExperienceInfo.WorkExperienceInfoDto;
import com.example.demo.model.WorkExperienceInfo;

import java.util.List;

public interface WorkExperienceInfoService {
    void createWorkExperienceInfo(CreateWorkExperienceInfoDto dto, Long id);

    void editWorkExperienceInfo(EditWorkExperienceDto dto, Long id);

    WorkExperienceInfoDto toDto(WorkExperienceInfo workExperienceInfo);
    List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Long resumeId);
}
