package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public User findUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE PHONE_NUMBER = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserByEmail(String email){
        String sql = "SELECT * FROM users WHERE PHONE_NUMBER = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }


}
