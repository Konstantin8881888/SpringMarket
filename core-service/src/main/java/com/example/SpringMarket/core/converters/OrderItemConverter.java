package com.example.SpringMarket.core.converters;

import com.example.SpringMarket.api.OrderItemDto;
import com.example.SpringMarket.core.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .pricePerProduct(orderItem.getPricePerProduct())
                .productTitle(orderItem.getProduct().getTitle())
                .build();
        return orderItemDto;
    }
}
