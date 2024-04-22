package com.example.demo.service.impl;

import com.example.demo.dao.ContactTypeDao;
import com.example.demo.dao.ContactsDao;
import com.example.demo.dto.ContactsInfo.CreateContactsInfoDto;
import com.example.demo.dto.ContactsInfo.EditContactsInfoDto;
import com.example.demo.model.ContactInfo;
import com.example.demo.service.ContactInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactsDao contactInfoDao;
    private final ContactTypeDao contactTypeDao;
    @Override
    public void createContactInfo(CreateContactsInfoDto contactsInfoDto, Long resumeId) {
        Long typeId = contactTypeDao.findOrCreateType(contactsInfoDto.getTypeName());

        ContactInfo contactInfo = ContactInfo.builder()
                .typeId(typeId)
                .resumeId(resumeId)
                .content(contactsInfoDto.getValue())
                .build();

        contactInfoDao.addContact(contactInfo);
    }

    @Override
    public void updateContact(EditContactsInfoDto contactsInfoDto, Long resumeId) {
        Long typeId = contactTypeDao.findOrCreateType(contactsInfoDto.getTypeName());

        ContactInfo contactInfo = ContactInfo.builder()
                .typeId(typeId)
                .resumeId(resumeId)
                .content(contactsInfoDto.getValue())
                .build();
        contactInfoDao.updateContact(contactInfo);
    }

}
