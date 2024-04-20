package com.example.demo.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ContactTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public Long findOrCreateType(String type) {
        Long typeId;
            try {
                String findSql = "SELECT id FROM contact_types WHERE type = ?";
                typeId = jdbcTemplate.queryForObject(findSql, new Object[]{type}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            String insertSql = "INSERT INTO contact_types (type) VALUES (?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
                ps.setString(1, type);
                return ps;
            }, keyHolder);
            typeId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        }
        return typeId;
    }
}
