package com.example.demo.service;

import com.example.demo.dto.ContactsInfo.ContactsInfoDto;
import com.example.demo.dto.ContactsInfo.CreateContactsInfoDto;
import com.example.demo.dto.ContactsInfo.EditContactsInfoDto;
import com.example.demo.model.ContactInfo;

import java.util.List;

public interface ContactInfoService {
    void createContactInfo(CreateContactsInfoDto contactsInfoDto, Long resumeId);

    void updateContact(EditContactsInfoDto contactsInfoDto, Long resumeId);

    ContactsInfoDto toDto(ContactInfo info);

    List<ContactsInfoDto> getContactsByResumeId(Long resumeId);
}
