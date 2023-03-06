package com.example.SpringMarket.carts.convertes;

import com.example.SpringMarket.api.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.SpringMarket.api.CartDto;
import com.example.SpringMarket.carts.model.Cart;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartConverter
{
    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems((List<CartItemDto>) cart.getItems());
        return cartDto;
    }
}
