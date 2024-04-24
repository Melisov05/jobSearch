package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.ContactsInfo.ContactsInfoDto;
import com.example.demo.dto.educationInfo.EducationInfoDto;
import com.example.demo.dto.resume.CreateResumeDto;
import com.example.demo.dto.resume.EditResumeDto;
import com.example.demo.dto.resume.ResumeDto;
import com.example.demo.dto.workExperienceInfo.WorkExperienceInfoDto;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Resume;
import com.example.demo.model.User;

import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void createResume(CreateResumeDto resumeDto) {
        Category category = categoryService.getCategoryByName(resumeDto.getCategoryName());
        User user = userService.findUserByEmail(resumeDto.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User was not found"));
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
    public void editResume(EditResumeDto resumeDto){
        Category category = categoryService.getCategoryByName(resumeDto.getCategoryName());
        User user = userService.findUserByEmail(resumeDto.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User was not found"));
        if(category == null){
            throw new CategoryNotFoundException();
        }

        Resume existingResume = resumeDao.getResumeByUserId(user.getId())
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found for user" +
                        " with email: " + user.getEmail()));

        existingResume.setName(resumeDto.getName());
        existingResume.setCategoryId(category.getId());
        existingResume.setSalary(resumeDto.getSalary());
        existingResume.setIsActive(resumeDto.getIsActive());

        resumeDao.updateResume(existingResume);

        resumeDto.getEducationInfo().forEach(e -> educationInfoService
                .editEducationInfo(e, existingResume.getId()));
        resumeDto.getWorkExpInfo().forEach(e -> workExperienceInfoService
                .editWorkExperienceInfo(e, existingResume.getId()));
        resumeDto.getContacts().forEach(e -> contactInfoService
                .updateContact(e, existingResume.getId()));
    }

    @Override
    public Boolean deleteResume(Long id) {
        if(resumeDao.getResumeById(id).isPresent()){
            resumeDao.deleteResume(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ResumeDto> getResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        return getListResume(resumes);
    }

    @Override
    public List<ResumeDto> getResumesByPosition(String position) {
        List<Resume> resumes = resumeDao.getResumesByPosition(position);
        return getListResume(resumes);
    }

    private List<ResumeDto> getListResume(List<Resume> resumes){
        List<ResumeDto> resumeDtos = new ArrayList<>();

        for(Resume resume : resumes){
            ResumeDto resumeDto = toDto(resume);
            resumeDtos.add(resumeDto);
        }
        return resumeDtos;
    }

    private ResumeDto toDto(Resume resume){
        Category category = categoryService.getCategoryById(resume.getCategoryId());
        String email = userService.getEmailByUserId(resume.getApplicantId());
        if(category == null){
            throw new CategoryNotFoundException("No category found");
        }

        List<WorkExperienceInfoDto> workExperienceInfoDtos = workExperienceInfoService
                .getWorkExperiencesByResumeId(resume.getId());
        List<EducationInfoDto> educationInfoDtoList = educationInfoService
                .getEducationInfoByResumeId(resume.getId());
        List<ContactsInfoDto> contactsInfoDtoList = contactInfoService.getContactsByResumeId(resume.getId());

        return ResumeDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .category(category.getName())
                .userEmail(email)
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateDate(resume.getUpdatedDate())
                .workExpInfo(workExperienceInfoDtos)
                .educationInfo(educationInfoDtoList)
                .contacts(contactsInfoDtoList)
                .build();
    }
}
