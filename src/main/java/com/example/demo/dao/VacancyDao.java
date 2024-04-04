package com.example.demo.dao;

import com.example.demo.model.Vacancy;
import lombok.AllArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    public List<Vacancy> getAllVacanciesByName(String name){
        String sql = """
                select * from VACANCIES where NAME ilike ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), ("%" + name + "%"));
    }

    public void create(Vacancy vacancy){
        String sql = """
                insert into VACANCIES(name, description, category_id, 
                salary, exp_from, exp_to, is_active, author_id, created_date, updated_time)
                 values (:name, :description, :category_id,
                  :salary, :exp_from, :exp_to, :is_active, :author_id, :created_date, :updated_time)
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("category_id", vacancy.getCategoryId())
                .addValue("salary", vacancy.getSalary())
                .addValue("exp_from", vacancy.getExpFrom())
                .addValue("exp_to", vacancy.getExpTo())
                .addValue("is_active", vacancy.getIsActive())
                .addValue("author_id", vacancy.getAuthorId())
                .addValue("created_date", LocalDateTime.now())
                .addValue("updated_time",  LocalDateTime.now()));
    }

    public void deleteVacancy(long vacancyId){
        String deleteFromRespondedApplicants = """
                delete from RESPONDED_APPLICANTS where VACANCY_ID = ?
                """;
        jdbcTemplate.update(deleteFromRespondedApplicants, vacancyId);

        String sql = """
                delete from VACANCIES where ID = ?
                """;
        jdbcTemplate.update(sql, vacancyId);
    }

    public Optional<Vacancy> getById(Long id) {
        String sql = """
                      select * from VACANCIES
                      where id = ?;          
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }

    public void editVacancy(Vacancy vacancy){
        String sql = """
                update VACANCIES set NAME = ?, DESCRIPTION = ?,
                 CATEGORY_ID = ?, SALARY = ?, EXP_FROM = ?,
                  EXP_TO = ?, IS_ACTIVE = ?, UPDATED_TIME = ? where ID = ?
                """;
        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getDescription(),
                vacancy.getCategoryId(), vacancy.getSalary(),
                vacancy.getExpFrom(), vacancy.getExpTo(),
                vacancy.getIsActive(),LocalDateTime.now(), vacancy.getId());
    }
}
