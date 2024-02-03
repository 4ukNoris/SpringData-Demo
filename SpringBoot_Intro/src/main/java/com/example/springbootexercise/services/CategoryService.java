package com.example.springbootexercise.services;

import com.example.springbootexercise.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
