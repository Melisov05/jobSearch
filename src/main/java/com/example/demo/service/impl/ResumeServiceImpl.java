package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.resume.CreateResumeDto;
import com.example.demo.dto.resume.ResumeDto;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final CategoryService categoryService;
    private final UserService userService;
    private final EducationInfoService educationInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;

    @Override
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

    }
}
