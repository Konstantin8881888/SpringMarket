package com.example.SpringMarket.core.services;

import com.example.SpringMarket.core.repositories.CategoryRepository;
import com.example.SpringMarket.core.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title)
    {
        return categoryRepository.findByTitle(title);
    }
}
