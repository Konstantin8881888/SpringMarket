package com.example.SpringMarket.carts.services;

import com.example.SpringMarket.carts.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public Cart getCart(String key) {
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, new Cart());
        }
        return (Cart)redisTemplate.opsForValue().get(key);
    }

    public void setCart(String key, Cart cart) {
        redisTemplate.opsForValue().set(key, cart);
    }
}