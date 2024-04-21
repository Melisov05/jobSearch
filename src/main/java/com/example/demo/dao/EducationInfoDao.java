package com.example.demo.dao;

import com.example.demo.model.EducationInfo;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EducationInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void createEducationInfo(EducationInfo educationInfo) {
        String sql = "insert into EDUCATION_INFO(RESUME_ID, INSTITUTION, PROGRAM, START_DATE, END_DATE, DEGREE) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, educationInfo.getResumeId(), educationInfo.getInstitution(),
                educationInfo.getProgram(), educationInfo.getStartDate(),
                educationInfo.getEndDate(), educationInfo.getDegree());
    }

    public void updateEducationInfo(EducationInfo educationInfo) {
        String sql = "update EDUCATION_INFO set INSTITUTION = ?, PROGRAM = ?, START_DATE = ?, " +
                "END_DATE = ?, DEGREE = ? WHERE RESUME_ID = ?";
        jdbcTemplate.update(sql, educationInfo.getInstitution(), educationInfo.getProgram(),
                educationInfo.getStartDate(), educationInfo.getEndDate(),
                educationInfo.getDegree(), educationInfo.getId());
    }
}
