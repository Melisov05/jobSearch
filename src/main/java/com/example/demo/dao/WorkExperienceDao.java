package com.example.demo.dao;

import com.example.demo.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkExperienceDao {

    private final JdbcTemplate jdbcTemplate;

    public void createWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
        String sql = "insert into WORK_EXPERIENCE_INFO (YEARS, COMPANY_NAME, POSITION, RESPONSIBILITIES, RESUME_ID) " +
                "values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, workExperienceInfo.getYears(), workExperienceInfo.getCompanyName(),
                workExperienceInfo.getPosition(), workExperienceInfo.getResponsibilities(),
                workExperienceInfo.getResumeId());
    }

    public void updateWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
        String sql = "update WORK_EXPERIENCE_INFO set YEARS = ?, COMPANY_NAME = ?, POSITION = ?, " +
                "RESPONSIBILITIES = ? where ID = ?";
        jdbcTemplate.update(sql, workExperienceInfo.getYears(), workExperienceInfo.getCompanyName(),
                workExperienceInfo.getPosition(), workExperienceInfo.getResponsibilities(),
                workExperienceInfo.getId());
    }

    public List<WorkExperienceInfo> getWorkExperienceInfoById(Long resumeId){
        String sql = """
                select * from WORK_EXPERIENCE_INFO where RESUME_ID = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class), resumeId);
    }
}

