package com.example.SpringMarket.carts.services;


import com.example.SpringMarket.carts.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService
{
    private Cart tempCart;
    private final ProductService productService;

    @PostConstruct
    public void init()
    {
        tempCart = new Cart();
    }

    public Cart getCurrentCart()
    {
        return tempCart;
    }

    public void add(Long productId)
    {
        Product product = productService.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Невозможно найти для добавления в корзину продукт с ID = " + productId));
        tempCart.add(product);
    }

    public void remove(Long productId)
    {
        tempCart.remove(productId);
    }
    public void clear()
    {
        tempCart.clear();
    }
}
