package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String accountType;
}
