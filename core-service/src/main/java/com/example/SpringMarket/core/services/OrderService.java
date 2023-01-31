package com.example.SpringMarket.core.services;

import com.example.SpringMarket.core.entities.User;
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
