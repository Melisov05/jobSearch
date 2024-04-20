package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactInfo {
    private Long id;
    private Long typeId;
    private Long resumeId;
    private String content;
}
