package com.example.demo.service.impl;

import com.example.demo.dao.WorkExperienceDao;
import com.example.demo.dto.workExperienceInfo.CreateWorkExperienceInfoDto;
import com.example.demo.model.WorkExperienceInfo;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void editWorkExperienceInfo(CreateWorkExperienceInfoDto dto, Long id){
        WorkExperienceInfo model = WorkExperienceInfo.builder()
                .responsibilities(dto.getResponsibilities())
                .position(dto.getPosition())
                .companyName(dto.getCompanyName())
                .years(dto.getYears())
                .resumeId(id)
                .build();
        workExperienceDao.updateWorkExperienceInfo(model);
    }

}
