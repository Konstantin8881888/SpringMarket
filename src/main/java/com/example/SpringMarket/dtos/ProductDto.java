package com.example.SpringMarket.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto
{
    private Long id;
    private String title;
    private int price;
}

