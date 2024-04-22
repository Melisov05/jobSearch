package com.example.demo.dto.resume;

import com.example.demo.dto.ContactsInfo.CreateContactsInfoDto;
import com.example.demo.dto.ContactsInfo.EditContactsInfoDto;
import com.example.demo.dto.educationInfo.CreateEducationInfoDto;
import com.example.demo.dto.educationInfo.EditEducationInfoDto;
import com.example.demo.dto.workExperienceInfo.CreateWorkExperienceInfoDto;
import com.example.demo.dto.workExperienceInfo.EditWorkExperienceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditResumeDto {
    private String name;
    private String categoryName;
    private String userEmail;
    private Double salary;
    private Boolean isActive;
    private List<EditWorkExperienceDto> workExpInfo;
    private List<EditEducationInfoDto> educationInfo;
    private List<EditContactsInfoDto> contacts;
}
