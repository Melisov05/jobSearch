package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Message {
    private int id;
    private int respondedApplicants;
    private String messages;
    private LocalDateTime timestamp;
}
