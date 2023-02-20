package com.example.SpringMarket.core.converters;

import com.example.SpringMarket.api.OrderDto;
import com.example.SpringMarket.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order)
    {
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .address(order.getAddress())
                .phone(order.getPhone())
                .totalPrice(order.getTotalPrice())
                .username(order.getUsername())
                .items(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()))
                .build();
        return orderDto;
    }
}
