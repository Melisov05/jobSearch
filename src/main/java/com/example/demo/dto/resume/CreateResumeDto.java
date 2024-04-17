package com.example.demo.dto.resume;

import com.example.demo.dto.ContactsInfoDto;
import com.example.demo.dto.CreateEducationInfoDto;
import com.example.demo.dto.WorkExperienceInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateResumeDto {
    private String name;
    private String categoryName;
    private String userEmail;
    private Double salary;
    private Boolean isActive;
    private List<WorkExperienceInfoDto> workExpInfo;
    private List<CreateEducationInfoDto> educationInfo;
    private List<ContactsInfoDto> contacts;
}
