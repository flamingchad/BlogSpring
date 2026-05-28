package com.example.blogspring.services;

import com.example.blogspring.entities.Category;
import com.example.blogspring.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }
}
