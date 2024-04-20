package com.example.demo.service;

import com.example.demo.dto.ContactsInfo.ContactsInfoDto;
import com.example.demo.dto.ContactsInfo.CreateContactsInfoDto;

public interface ContactInfoService {
    void createContactInfo(CreateContactsInfoDto contactsInfoDto, Long resumeId);
}
