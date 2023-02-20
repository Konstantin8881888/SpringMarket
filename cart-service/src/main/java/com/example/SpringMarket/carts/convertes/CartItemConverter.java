package com.example.SpringMarket.carts.convertes;

import com.example.SpringMarket.carts.model.CartItem;
import org.springframework.stereotype.Component;
import com.example.SpringMarket.api.CartItemDto;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = CartItemDto.builder()
                .price(cartItem.getPrice())
                .pricePerProduct(cartItem.getPricePerProduct())
                .quantity(cartItem.getQuantity())
                .productTitle(cartItem.getProductTitle())
                .productId(cartItem.getProductId())
                .build();
        return cartItemDto;
    }
}
