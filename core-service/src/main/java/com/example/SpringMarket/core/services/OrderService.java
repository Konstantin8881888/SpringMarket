package com.example.SpringMarket.core.services;

import com.example.SpringMarket.api.ResourceNotFoundException;
import com.example.SpringMarket.core.entities.Product;
import com.example.SpringMarket.core.integrations.CartServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.SpringMarket.api.CartDto;
import com.example.SpringMarket.core.entities.Order;
import com.example.SpringMarket.core.entities.OrderItem;
import com.example.SpringMarket.core.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public Order createOrder(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username null or empty!");
        }
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItem -> {
                    Optional<Product> productOptional = productService.findById(cartItem.getProductId());
                    if (productOptional.isPresent())
                    {
                        return new OrderItem(
                                productOptional.get(),
                                order,
                                cartItem.getQuantity(),
                                cartItem.getPricePerProduct(),
                                cartItem.getPrice()
                        );
                    } else
                    {
                        throw new ResourceNotFoundException("Product with id " + cartItem.getProductId() + " not found");
                    }
                }
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clear(username);
        return order;
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
}
