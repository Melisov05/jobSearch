package com.example.demo.dao;

import com.example.demo.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private Category getCategoryByName(String categoryName){
        String sql = """
                select * from CATEGORIES where NAME = ?
                """;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), categoryName);
    }

    private Category getCategoryById(long categoryId){
        String sql = """
                select * from CATEGORIES where ID = ?
                """;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), categoryId);
    }
}
