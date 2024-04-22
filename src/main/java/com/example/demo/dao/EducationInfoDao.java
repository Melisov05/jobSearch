package com.example.demo.dao;

import com.example.demo.model.EducationInfo;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
                "END_DATE = ?, DEGREE = ? WHERE ID = ?";
        jdbcTemplate.update(sql, educationInfo.getInstitution(), educationInfo.getProgram(),
                educationInfo.getStartDate(), educationInfo.getEndDate(),
                educationInfo.getDegree(), educationInfo.getId());
    }

    public List<EducationInfo> getEducationInfoByResumeId(Long id){
        String sql = """
                select * from EDUCATION_INFO where RESUME_ID = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), id);
    }
}
