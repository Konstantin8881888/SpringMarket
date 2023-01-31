package com.example.SpringMarket.carts.controllers;


import com.example.SpringMarket.carts.model.Cart;
import com.example.SpringMarket.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController
{
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id)
    {
        cartService.add(id);
    }

    @GetMapping("/clear")
    public void clearCart()
    {
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id)
    {
        cartService.remove(id);
    }

    @GetMapping
    public Cart getCurrentCart()
    {
        return cartService.getCurrentCart();
    }
}
