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

    public List<Resume> findResumesByCategory(String category){
        String sql = """
                    select * from resumes where category_id = (select id from categories where name = ?)
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category);
    }

    public List<Resume> findResumesByUser(long userId){
        String sql = """
                select * from resumes where applicant_id = ? 
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }
}
