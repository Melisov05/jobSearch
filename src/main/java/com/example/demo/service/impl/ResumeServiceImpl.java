package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.resume.CreateResumeDto;
import com.example.demo.dto.resume.EditResumeDto;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ResumeNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final CategoryService categoryService;
    private final UserService userService;
    private final EducationInfoService educationInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final ContactInfoService contactInfoService;

    @Override
    @Transactional
    public void createResume(CreateResumeDto resumeDto) {
        Category category = categoryService.getCategoryByName(resumeDto.getCategoryName());
        User user = userService.findUserByEmail(resumeDto.getUserEmail());
        if(category == null){
            throw new CategoryNotFoundException();
        }
        Resume resume = Resume.builder()
                .name(resumeDto.getName())
                .categoryId(category.getId())
                .applicantId(user.getId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .build();
        Long id = resumeDao.addResume(resume);
        resumeDto.getEducationInfo().forEach(e -> educationInfoService.createEducationInfo(e, id));
        resumeDto.getWorkExpInfo().forEach(e -> workExperienceInfoService.createWorkExperienceInfo(e, id));
        resumeDto.getContacts().forEach(e -> contactInfoService.createContactInfo(e, id));
    }

    @Override
    @Transactional
    public void editResume(EditResumeDto resumeDto){
        Category category = categoryService.getCategoryByName(resumeDto.getCategoryName());
        User user = userService.findUserByEmail(resumeDto.getUserEmail());
        if(category == null){
            throw new CategoryNotFoundException();
        }

        Resume existingResume = resumeDao.getResumeByUserId(user.getId())
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found for user with email: " + user.getEmail()));

        existingResume.setName(resumeDto.getName());
        existingResume.setCategoryId(category.getId());
        existingResume.setSalary(resumeDto.getSalary());
        existingResume.setIsActive(resumeDto.getIsActive());
        resumeDao.updateResume(existingResume);
        resumeDto.getEducationInfo().forEach(e -> educationInfoService.editEducationInfo(e, existingResume.getId()));
        resumeDto.getWorkExpInfo().forEach(e -> workExperienceInfoService.editWorkExperienceInfo(e, existingResume.getId()));
    }
}
