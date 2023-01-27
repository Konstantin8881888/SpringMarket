package com.example.SpringMarket.services;

import com.example.SpringMarket.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final ProductService productService;
    public void createOrder(User user)
    {

    }
}
