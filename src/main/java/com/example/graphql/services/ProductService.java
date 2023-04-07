package com.example.graphql.services;

import com.example.graphql.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * @Created 07 04 2023 - 8:51 AM
 * @Author Hazeem Hassan
 */
public interface ProductService {
    Product getById(UUID id);
    List<Product> getAll();
    Product create(Product product);
    Product update(UUID productId,Product product);
    Product remove(UUID id);

    Boolean productExistsOperation(UUID productId);
}
