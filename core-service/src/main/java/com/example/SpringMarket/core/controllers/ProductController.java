package com.example.SpringMarket.core.controllers;

import com.example.SpringMarket.core.converters.ProductConverter;
import com.example.SpringMarket.core.dtos.ProductDto;
import com.example.SpringMarket.core.entities.Product;
import com.example.SpringMarket.core.exceptions.ResourceNotFoundException;
import com.example.SpringMarket.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts()
    {
        return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
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
        return productConverter.entityToDto(p);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto)
    {
        Product p = productService.createNewProduct(productDto);
        return productConverter.entityToDto(p);
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
