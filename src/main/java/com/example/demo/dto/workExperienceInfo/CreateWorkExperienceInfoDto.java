package com.example.demo.dto.workExperienceInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkExperienceInfoDto {
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
