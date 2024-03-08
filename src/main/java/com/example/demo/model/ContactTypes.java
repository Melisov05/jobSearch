package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactTypes {
    private int id;
    private String type;
}
