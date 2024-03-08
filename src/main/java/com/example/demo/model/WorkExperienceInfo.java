package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorkExperienceInfo {
    private int id;
    private int resumeId;
    private int years;
    private String companyName;
    private String position;
    private String responsibilities;
}
