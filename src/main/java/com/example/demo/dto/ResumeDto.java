package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Long id;
    private String name;
    private String category;
    private String userEmail;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private List<WorkExperienceInfoDto> workExpInfo;
    private List<EducationInfoDto> educationInfo;
    private List<ContactsInfoDto> contacts;
}
