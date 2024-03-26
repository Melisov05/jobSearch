package com.example.demo.dao;

import com.example.demo.model.Resume;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Resume> findResumesByCategory(String categoryName){
        String sql = """
                    select * from resumes where category_id = (select id from categories where name = ?)
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryName);
    }

    public List<Resume> findResumesByUserId(long userId){
        String sql = """
                select * from resumes where applicant_id = ? 
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    // do we need to get resume by its id?
    public Resume getResumeById(long resumeId){
        String sql = """
                select * from RESUMES where ID = ?;
                """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), resumeId);
    }

    public void addResume(Resume resume){
        String sql = """
                insert into RESUMES(name, applicant_id, category_id, salary, is_active, created_date, updated_date)
                 values (?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, resume.getName(), resume.getApplicantId(), resume.getCategoryId(),
                resume.getSalary(), resume.isActive(), resume.getCreatedDate(), resume.getUpdatedDate());
    }

    public void updateResume(Resume resume){
        String sql = "update resumes set name = ?, applicant_id = ?, category_id = ?, salary = ?, " +
                "is_active = ?, updated_date = ? where id = ?";
        jdbcTemplate.update(sql, resume.getName(), resume.getApplicantId(), resume.getCategoryId(),
                resume.getSalary(), resume.isActive(), resume.getUpdatedDate(), resume.getId());
    }

    public void deleteResume(long resumeId){
        String sql = """
               delete from RESUMES where ID = ?
                """;
        jdbcTemplate.update(sql, resumeId);
    }


}
