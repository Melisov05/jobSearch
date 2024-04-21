package com.example.demo.dao;

import com.example.demo.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsDao {
    private final JdbcTemplate template;

    public void addContact(ContactInfo contactInfo) {
        String sql = "INSERT INTO CONTACT_INFO (type_id, resume_id, CONTENT) VALUES (?, ?, ?)";
        template.update(
                sql,
                contactInfo.getTypeId(),
                contactInfo.getResumeId(),
                contactInfo.getContent()
        );
    }

    public void updateContact(ContactInfo contactInfo) {
        String sql = "UPDATE CONTACT_INFO SET type_id = ?, resume_id = ?, CONTENT = ? WHERE id = ?";
        template.update(
                sql,
                contactInfo.getTypeId(),
                contactInfo.getResumeId(),
                contactInfo.getContent(),
                contactInfo.getId()
        );
    }

}
