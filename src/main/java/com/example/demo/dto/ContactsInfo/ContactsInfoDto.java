package com.example.demo.dto.ContactsInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsInfoDto {
    private Long id;
    private String typeName;
    private Long resumeId;
    private String value;
}
