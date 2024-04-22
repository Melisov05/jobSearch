package com.example.demo.service.impl;

import com.example.demo.dao.WorkExperienceDao;
import com.example.demo.dto.workExperienceInfo.CreateWorkExperienceInfoDto;
import com.example.demo.dto.workExperienceInfo.EditWorkExperienceDto;
import com.example.demo.dto.workExperienceInfo.WorkExperienceInfoDto;
import com.example.demo.model.WorkExperienceInfo;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {
    private final WorkExperienceDao workExperienceDao;
    @Override
    public void createWorkExperienceInfo(CreateWorkExperienceInfoDto dto, Long id) {
        WorkExperienceInfo model = WorkExperienceInfo.builder()
                .resumeId(id)
                .years(dto.getYears())
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .responsibilities(dto.getResponsibilities())
                .build();
        workExperienceDao.createWorkExperienceInfo(model);
    }
    @Override
    public void editWorkExperienceInfo(EditWorkExperienceDto dto, Long id){
        WorkExperienceInfo model = WorkExperienceInfo.builder()
                .id(dto.getId())
                .responsibilities(dto.getResponsibilities())
                .position(dto.getPosition())
                .companyName(dto.getCompanyName())
                .years(dto.getYears())
                .resumeId(id)
                .build();
        workExperienceDao.updateWorkExperienceInfo(model);
    }

    public List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Long resumeId){
        List<WorkExperienceInfo> workExperienceInfoList = workExperienceDao.getWorkExperienceInfoById(resumeId);
        return  workExperienceInfoList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public WorkExperienceInfoDto toDto(WorkExperienceInfo workExperienceInfo) {
        return WorkExperienceInfoDto.builder()
                .companyName(workExperienceInfo.getCompanyName())
                .responsibilities(workExperienceInfo.getResponsibilities())
                .years(workExperienceInfo.getYears())
                .position(workExperienceInfo.getPosition())
                .build();
    }

}
