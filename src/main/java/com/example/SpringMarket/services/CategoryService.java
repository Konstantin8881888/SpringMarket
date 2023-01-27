package com.example.SpringMarket.services;

import com.example.SpringMarket.entities.Category;
import com.example.SpringMarket.entities.Product;
import com.example.SpringMarket.repositories.CategoryRepository;
import com.example.SpringMarket.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
