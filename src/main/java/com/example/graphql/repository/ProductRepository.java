package com.example.graphql.repository;

import com.example.graphql.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @Created 07 04 2023 - 8:47 AM
 * @Author Hazeem Hassan
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
