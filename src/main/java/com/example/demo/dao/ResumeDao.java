package com.example.demo.dao;

import com.example.demo.model.Resume;
import lombok.AllArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<Resume> getResumeById(long resumeId){
        String sql = """
                select * from RESUMES where ID = ?;
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), resumeId)
                )
        );
    }

    public Optional<Resume> getResumeByUserId(long userId){
        String sql = """
                select * from RESUMES where APPLICANT_ID = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId)
                )
        );
    }

    public List<Resume> getAllActiveResumes(){
        String sql = """
                select * from RESUMES where IS_ACTIVE = 'true'
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getAllResumes(){
        String sql = """
                select * from RESUMES
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }


    public Long addResume(Resume resume){
        String sql = """
                insert into RESUMES(name, applicant_id, category_id, salary, is_active, created_date, updated_date)
                 values (?, ?, ?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setString(1,resume.getName());
            ps.setLong(2,resume.getCategoryId());
            ps.setLong(3,resume.getApplicantId());
            ps.setDouble(4,resume.getSalary());
            ps.setBoolean(5,true);
            ps.setDate(6, Date.valueOf(LocalDate.now()));
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void updateResume(Resume resume){
        String sql = "update resumes set name = ?, applicant_id = ?, category_id = ?, salary = ?, " +
                "is_active = ?, updated_date = ? where id = ?";
        jdbcTemplate.update(sql, resume.getName(), resume.getApplicantId(), resume.getCategoryId(),
                resume.getSalary(), resume.getIsActive(), resume.getUpdatedDate(), resume.getId());
    }

    public void deleteResume(long resumeId){

        String deleteFromRespondedApplicants = """
                delete from RESPONDED_APPLICANTS where RESUME_ID = ?
                """;
        jdbcTemplate.update(deleteFromRespondedApplicants, resumeId);

        String deleteFromWorkExperience = """
                delete from WORK_EXPERIENCE_INFO where RESUME_ID = ?
                """;
        jdbcTemplate.update(deleteFromWorkExperience, resumeId);

        String deleteFromEducation = """
                delete from EDUCATION_INFO where RESUME_ID = ?
                """;
        jdbcTemplate.update(deleteFromEducation, resumeId);

        String deleteFromContactInfo = """
                delete from CONTACT_INFO where RESUME_ID = ?
                """;
        jdbcTemplate.update(deleteFromContactInfo, resumeId);

        String sql = """
               delete from RESUMES where ID = ?
                """;
        jdbcTemplate.update(sql, resumeId);
    }


}
