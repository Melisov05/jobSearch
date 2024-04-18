package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorkExperienceInfo {
    private Long id;
    private Long resumeId;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
