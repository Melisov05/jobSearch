package com.example.demo.service.impl;

import com.example.demo.dao.EducationInfoDao;
import com.example.demo.dto.CreateEducationInfoDto;
import com.example.demo.model.EducationInfo;
import com.example.demo.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;
    @Override
    public void createEducationInfo(CreateEducationInfoDto education, Long id) {
        EducationInfo model = EducationInfo.builder()
                .resumeId(id)
                .institution(education.getInstitution())
                .program(education.getProgram())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .program(education.getProgram())
                .degree(education.getDegree())
                .build();
        educationInfoDao.createEducationInfo(model);
    }
}
