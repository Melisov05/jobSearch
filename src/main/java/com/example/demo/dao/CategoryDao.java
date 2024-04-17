package com.example.demo.dao;

import com.example.demo.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    private List<Category> getCategories(){
        String sql = """
                select * from categories
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    public Optional<Category> getCategoryByName(String name){
        String sql = """
                select * from CATEGORIES
                where name ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Category.class),name)
                )
        );
    }

    private Category getCategoryById(long categoryId){
        String sql = """
                select * from CATEGORIES where ID = ?
                """;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), categoryId);
    }
}
