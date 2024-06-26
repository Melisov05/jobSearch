package com.example.demo.dao;

import com.example.demo.exception.ContactNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContactTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public Long findOrCreateType(String type) {
        Long typeId;
            try {
                String findSql = "SELECT id FROM contact_types WHERE type ilike ?";
                typeId = jdbcTemplate.queryForObject(findSql, new Object[]{type}, Long.class);
                return typeId;
        } catch (EmptyResultDataAccessException e) {
            String insertSql = "INSERT INTO contact_types (type) VALUES (?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
                ps.setString(1, type);
                return ps;
            }, keyHolder);
            typeId = Objects.requireNonNull(keyHolder.getKey()).longValue();
            return typeId;
        }
    }

    public String findTypeById(Long typeId) {
        try {
            String sql = "SELECT type FROM contact_types WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{typeId}, String.class);
        } catch (DataAccessException e) {
            log.error("Failed to find type by id: {}. Error: {}", typeId, e.getMessage());
            return String.valueOf(new ContactNotFoundException("find type by id didn't find anything"));
        }
    }
}
