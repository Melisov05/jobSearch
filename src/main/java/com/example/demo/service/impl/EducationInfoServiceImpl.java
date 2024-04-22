package com.example.demo.service.impl;

import com.example.demo.dao.EducationInfoDao;
import com.example.demo.dto.educationInfo.CreateEducationInfoDto;
import com.example.demo.dto.educationInfo.EditEducationInfoDto;
import com.example.demo.dto.educationInfo.EducationInfoDto;
import com.example.demo.dto.workExperienceInfo.WorkExperienceInfoDto;
import com.example.demo.model.EducationInfo;
import com.example.demo.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void editEducationInfo(EditEducationInfoDto education, Long id) {
        EducationInfo model = EducationInfo.builder()
                .id(education.getId())
                .resumeId(id)
                .institution(education.getInstitution())
                .degree(education.getDegree())
                .program(education.getProgram())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .build();
        educationInfoDao.updateEducationInfo(model);
    }


    @Override
    public EducationInfoDto toDto(EducationInfo info) {
        return EducationInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResumeId())
                .program(info.getProgram())
                .institution(info.getInstitution())
                .degree(info.getDegree())
                .startDate(info.getStartDate())
                .endDate(info.getEndDate())
                .build();
    }

    @Override
    public List<EducationInfoDto> getEducationInfoByResumeId(Long resumeId) {
        List<EducationInfo> educationInfoList = educationInfoDao.getEducationInfoByResumeId(resumeId);
        return educationInfoList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
