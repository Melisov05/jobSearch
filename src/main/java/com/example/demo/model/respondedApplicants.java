package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class respondedApplicants {
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
}
