package com.example.demo.service;

import com.example.demo.model.Category;

public interface CategoryService {
    Category getCategoryByName(String category);
    Category getCategoryById(Long id);
}
