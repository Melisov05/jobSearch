package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public User findUserByName(String name) {
        String sql = "select * from users where name = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserByPhone(String phone) {
        String sql = "select * from users where PHONE_NUMBER = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserByEmail(String email){
        String sql = "select * from users where EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),email);
    }

    public boolean checkUserExistsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }

    public List<User> findApplicantsForVacancy(long user){
        String sql = """
                select * from RESPONDED_APPLICANTS where
                 RESUME_ID = (select id from RESUMES where APPLICANT_ID = ?)
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), user);
    }

    public void updateProfile(User user) {
        String sql = "update users set name = ?," +
                " surname = ?, password = ?, age = ?, phone_number = ?," +
                " avatar = ?, account_type = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(),
                user.getPassword(),
                user.getAge(), user.getPhoneNumber(), user.getAvatar(),
                user.getAccountType(), user.getId());
    }

    public Optional<User> findUserById(Long id){
        String sql = """
               select * from USERS where ID = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }

    public List<User> getCandidates(){
        String sql = """
                select * from USERS where ACCOUNT_TYPE = 'candidate'
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getEmployers() {
        String sql = """
                select * from USERS where ACCOUNT_TYPE = 'employer'
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getEmployersByName(String name) {
        String sql = """
                select * from USERS where ACCOUNT_TYPE = 'employer'and NAME ilike ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%");
    }

    public List<User> getCandidatesByName(String name) {
        String sql = """
                select * from USERS where ACCOUNT_TYPE = 'candidate'and NAME ilike ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%");
    }
}
