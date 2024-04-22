package com.example.demo.dto.ContactsInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditContactsInfoDto {
    private String typeName;
    private String value;
}
