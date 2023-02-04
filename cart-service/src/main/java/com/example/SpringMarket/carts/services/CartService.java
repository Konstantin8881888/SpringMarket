package com.example.SpringMarket.carts.services;

import com.example.SpringMarket.carts.integrations.ProductServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.SpringMarket.api.ProductDto;
import com.example.SpringMarket.carts.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void remove(Long productId) {
        tempCart.remove(productId);
    }

    public void clear() {
        tempCart.clear();
    }
}
