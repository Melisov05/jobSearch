package com.example.demo.dao;

import com.example.demo.model.RespondedApplicant;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor

public class RespondedApplicantDao {
    private final JdbcTemplate template;

    public List<RespondedApplicant> getRespondedApplicants() {
        String sql = """
                select * from RESPONDED_APPLICANTS
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class));
    }
}
