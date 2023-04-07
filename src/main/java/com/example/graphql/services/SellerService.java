package com.example.graphql.services;

import com.example.graphql.model.Seller;


import java.util.List;
import java.util.UUID;

/**
 * @Created 07 04 2023 - 8:51 AM
 * @Author Hazeem Hassan
 */
public interface SellerService {
    Seller createSeller(Seller seller);
    Seller getById(UUID sellerId);
    List<Seller> findAll();
    Seller remove(UUID id);
    Seller update(UUID sellerId, Seller seller);
    Boolean sellerExistsOperation(UUID id);
}
