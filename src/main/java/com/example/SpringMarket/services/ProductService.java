package com.example.SpringMarket.services;

import com.example.SpringMarket.entities.Product;
import com.example.SpringMarket.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;

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
}
