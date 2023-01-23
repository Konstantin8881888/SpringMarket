package com.example.SpringMarket.controllers;

import com.example.SpringMarket.dtos.ProductDto;
import com.example.SpringMarket.entities.Product;
import com.example.SpringMarket.exceptions.AppError;
import com.example.SpringMarket.exceptions.ResourceNotFoundException;
import com.example.SpringMarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts()
    {
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findProductById(@PathVariable Long id)
//    {
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent())
//        {
//            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Продукт не найден! Id: " + id), HttpStatus.NOT_FOUND);
//            return err;
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id)
    {
        Product p = productService.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Продукт не найден! Id: " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }

//    @ExceptionHandler
//    public ResponseEntity<AppError> exceptionHandler(ResourceNotFoundException e)
//    {
//        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id)
    {
        productService.deleteById(id);
    }

}
