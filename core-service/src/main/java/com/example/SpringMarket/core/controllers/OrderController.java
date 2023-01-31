package com.example.SpringMarket.core.controllers;

import com.example.SpringMarket.core.entities.User;
import com.example.SpringMarket.core.services.OrderService;
import com.example.SpringMarket.core.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController
{
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal)
    {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException());
        orderService.createOrder(user);
    }
}
