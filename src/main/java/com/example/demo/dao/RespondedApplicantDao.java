package com.example.demo.dao;

import com.example.demo.model.RespondedApplicant;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor

public class RespondedApplicantDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<RespondedApplicant> getRespondedApplicants() {
        String sql = """
                select * from RESPONDED_APPLICANTS
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class));
    }

    public void applyToVacancy(RespondedApplicant respondedApplicant) {
        String sql = """
            insert into RESPONDED_APPLICANTS(resume_id, vacancy_id, confirmation)
             VALUES (:resumeId, :vacancyId, :confirmation)
            """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", respondedApplicant.getResumeId())
                .addValue("vacancyId", respondedApplicant.getVacancyId())
                .addValue("confirmation", respondedApplicant.getConfirmation())
        );
    }

}
