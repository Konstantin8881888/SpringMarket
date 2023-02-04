package com.example.SpringMarket.carts.convertes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.SpringMarket.api.CartDto;
import com.example.SpringMarket.carts.model.Cart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()));
        return cartDto;
    }
}
