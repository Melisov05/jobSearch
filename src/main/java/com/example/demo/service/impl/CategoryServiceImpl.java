package com.example.demo.service.impl;

import com.example.demo.dao.CategoryDao;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    @Override
    public Category getCategoryByName(String category) {
        return categoryDao.getCategoryByName(category).orElseThrow(() -> new CategoryNotFoundException("Category by name "+category+" not found"));
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDao.getCategoryById(id);
    }
}
