package com.example.SpringMarket.core.test;

import com.example.SpringMarket.api.CartDto;
import com.example.SpringMarket.api.CartItemDto;
import com.example.SpringMarket.core.entities.Category;
import com.example.SpringMarket.core.entities.Order;
import com.example.SpringMarket.core.entities.Product;
import com.example.SpringMarket.core.integrations.CartServiceIntegration;
import com.example.SpringMarket.core.repositories.OrderRepository;
import com.example.SpringMarket.core.services.OrderService;
import com.example.SpringMarket.core.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest
{
    @Autowired
    private OrderService orderService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;
    @MockBean
    private ProductService productService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest()
    {
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductTitle("Juice");
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(120));
        cartItemDto.setQuantity(2);
        cartItemDto.setPrice(BigDecimal.valueOf(240));
        cartItemDto.setProductId(2244L);
        cartDto.setTotalPrice(BigDecimal.valueOf(240));
        cartDto.setItems(List.of(cartItemDto));

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart(null);

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Other");

        Product product = new Product();
        product.setId(2244L);
        product.setPrice(BigDecimal.valueOf(120));
        product.setTitle("Juice");
        product.setCategory(category);

        Mockito.doReturn(Optional.of(product)).when(productService).findById(2244L);


        Order order = orderService.createOrder("bob");
        Assertions.assertEquals(order.getTotalPrice(), 240);

        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
