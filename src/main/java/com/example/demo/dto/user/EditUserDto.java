package com.example.demo.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDto {
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String password;
    private String phoneNumber;
    private String accountType;
    private MultipartFile avatar;
}
