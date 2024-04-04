package com.example.demo.service.impl;

import com.example.demo.dao.RespondedApplicantDao;
import com.example.demo.dto.RespondedApplicantDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.service.RespondedApplicantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public List<RespondedApplicantDto> getRespondedApplicants() {
        List<RespondedApplicant> list = respondedApplicantDao.getRespondedApplicants();

        List<RespondedApplicantDto> applicantDtos = new ArrayList<>();
        for(RespondedApplicant respondedApplicant : list){
            RespondedApplicantDto respondedApplicantDto = toDto(respondedApplicant);
            applicantDtos.add(respondedApplicantDto);
        }
        return applicantDtos;
    }

    private RespondedApplicantDto toDto(RespondedApplicant applicant){
        return RespondedApplicantDto.builder()
                .id(applicant.getId())
                .resumeId(applicant.getResumeId())
                .vacancyId(applicant.getVacancyId())
                .confirmation(applicant.getConfirmation()).build();

    }
}
