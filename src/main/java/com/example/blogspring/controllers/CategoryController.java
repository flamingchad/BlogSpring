package com.example.blogspring.controllers;

import com.example.blogspring.dto.CategoryDto;
import com.example.blogspring.mappers.CategoryMapper;
import com.example.blogspring.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        return ResponseEntity.ok(categoryService.listCategories()
                .stream()
                .map(categoryMapper::toDto)
                .toList());
    }
}
