package com.example.springbootexercise.services;

import com.example.springbootexercise.models.Category;
import com.example.springbootexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();

        long size = this.categoryRepository.count();
        int categoryCount = random.nextInt((int) size) + 1;

        Set<Integer> categoriesIds = fillCategoriesSet(categoryCount, random, (int) size);

        List<Category> allById = this.categoryRepository.findAllById(categoriesIds);
        return new HashSet<>(allById);
    }

    private static Set<Integer> fillCategoriesSet(int categoryCount, Random random, int size) {
        Set<Integer> categoriesIds = new HashSet<>();
        for (int i = 0; i < categoryCount; i++) {
            int nextId = random.nextInt(size) + 1;
            categoriesIds.add(nextId);
        }
        return categoriesIds;
    }
}
