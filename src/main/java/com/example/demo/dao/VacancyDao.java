package com.example.demo.dao;

import com.example.demo.model.Vacancy;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> findVacanciesByRespondedUser(long userId){
        String sql = """
                select * from RESPONDED_APPLICANTS 
                where id in 
                (select VACANCY_ID from
                 RESPONDED_APPLICANTS where RESUME_ID in 
                (select id from RESUMES where APPLICANT_ID = ?))
                """;
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Vacancy.class), userId);
    }

    public List<Vacancy> getAllVacancies(){
        String sql = """
                select * from VACANCIES
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> findVacancyByCategory(String categoryName){
        String sql = """
                select * from VACANCIES where CATEGORY_ID = (select id from CATEGORIES where CATEGORIES.NAME = ?)
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryName);
    }

}
