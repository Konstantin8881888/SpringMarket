package com.example.SpringMarket.controllers;

import com.example.SpringMarket.entities.Product;
import com.example.SpringMarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts()
    {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id)
    {
        return productService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id)
    {
        productService.deleteById(id);
    }

}
