package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Builder
@Data
public class Resumes {
    private int id;
    private int applicantId;
    private String name;
    private int categoryId;
    private double salary;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
