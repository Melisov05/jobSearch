package com.example.demo.service;

import com.example.demo.dto.ContactsInfo.ContactsInfoDto;
import com.example.demo.dto.ContactsInfo.CreateContactsInfoDto;
import com.example.demo.dto.ContactsInfo.EditContactsInfoDto;
import com.example.demo.model.ContactInfo;

public interface ContactInfoService {
    void createContactInfo(CreateContactsInfoDto contactsInfoDto, Long resumeId);

    void updateContact(EditContactsInfoDto contactsInfoDto, Long resumeId);
}
