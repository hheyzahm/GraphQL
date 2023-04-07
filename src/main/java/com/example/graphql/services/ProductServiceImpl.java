package com.example.graphql.services;

import com.example.graphql.model.Product;
import com.example.graphql.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Created 07 04 2023 - 8:59 AM
 * @Author Hazeem Hassan
 */

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    public final ProductRepository productRepository;

    @Override
    public Product getById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(UUID productId, Product details) {

        Product product = productRepository.findById(productId).get();
        product.setTitle(details.getTitle());
        product.setDescription(details.getDescription());
        product.setPrice(details.getPrice());
        product.setSeller(details.getSeller());

        return productRepository.save(product);
    }

    @Override
    public Product remove(UUID id) {
        Optional<Product> removedProduct = productRepository.findById(id);
        if (removedProduct.isPresent()) {
            productRepository.deleteById(id);
        }
        return removedProduct.orElse(null);
    }

    public Boolean productExistsOperation(UUID id) {
        return productRepository.existsById(id);
    }
}
