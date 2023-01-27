package com.example.SpringMarket.repositories;

import com.example.SpringMarket.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Optional<Category> findByTitle(String title);
}
