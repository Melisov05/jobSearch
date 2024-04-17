package com.example.demo.service.impl;

import com.example.demo.dao.EducationInfoDao;
import com.example.demo.dto.EducationInfoDto;
import com.example.demo.model.EducationInfo;
import com.example.demo.service.EducationInfoService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;
    @Override
    public void createEducationInfo(EducationInfoDto education, Long id) {
        EducationInfo model = EducationInfo.builder()
                .resumeId(id)
                .institution(education.getInstitution())
                .program(education.getProgram())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .program(education.getProgram())
                .build();
        educationInfoDao.createEducationInfo(model);
    }
}
