package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactInfo {
    private int id;
    private int typeId;
    private int resumeId;
    private String value;
}
