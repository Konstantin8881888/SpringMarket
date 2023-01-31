package com.example.SpringMarket.core.services;

import com.example.SpringMarket.core.exceptions.ResourceNotFoundException;
import com.example.SpringMarket.core.repositories.ProductRepository;
import com.example.SpringMarket.core.dtos.ProductDto;
import com.example.SpringMarket.core.entities.Category;
import com.example.SpringMarket.core.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public List<Product> findAll()
    {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id)
    {
        return productRepository.findById(id);
    }

    public void deleteById(Long id)
    {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
