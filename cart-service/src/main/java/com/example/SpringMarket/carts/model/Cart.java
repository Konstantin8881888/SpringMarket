package com.example.SpringMarket.carts.model;

import lombok.Data;
import com.example.SpringMarket.api.ProductDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private Map<Long, CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new HashMap<>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public void add(ProductDto product) {
        CartItem item = items.get(product.getId());
        if (item != null) {
            item.changeQuantity(1);
        } else {
            item = new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
            items.put(product.getId(), item);
        }
        recalculate();
    }

    public void remove(Long productId) {
        if (items.remove(productId) != null) {
            recalculate();
        }
    }

    public void clear()
    {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items.values()) {
            totalPrice = totalPrice.add(item.getPrice());
        }
    }
}