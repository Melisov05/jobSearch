package com.example.demo.dto.educationInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditEducationInfoDto {
    private Long id;
    private String institution;
    private String degree;
    private String program;
    private LocalDate startDate;
    private LocalDate endDate;
}
