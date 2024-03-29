package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
