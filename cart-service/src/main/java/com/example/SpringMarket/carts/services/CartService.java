package com.example.SpringMarket.carts.services;

import com.example.SpringMarket.carts.integrations.ProductServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.SpringMarket.api.ProductDto;
import com.example.SpringMarket.carts.model.Cart;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisService redisService;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        return redisService.getCart(cartPrefix + uuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.add(product));
    }

    public void remove(String uuid, Long productId) {
        execute(uuid, cart -> cart.remove(productId));
    }

    public void clear(String uuid) {
        execute(uuid, Cart::clear);
    }

    private void execute(String uuid, Consumer<Cart> operation)
    {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisService.setCart(cartPrefix + uuid, cart);
    }
}
