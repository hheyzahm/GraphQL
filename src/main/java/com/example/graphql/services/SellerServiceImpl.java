package com.example.graphql.services;

import com.example.graphql.model.Product;
import com.example.graphql.model.Seller;
import com.example.graphql.repository.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Created 07 04 2023 - 9:02 AM
 * @Author Hazeem Hassan
 */


@Service
@AllArgsConstructor

public class SellerServiceImpl implements SellerService {
    @Autowired
    private final SellerRepository sellerRepository;

    @Override
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller remove(UUID id) {
        Optional<Seller> removedProduct = sellerRepository.findById(id);
        if (removedProduct.isPresent()) {
            sellerRepository.deleteById(id);
        }
        return removedProduct.orElse(null);
    }
    @Override
    public Seller getById(UUID id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return seller.orElse(null);
    }
    @Override
    public Seller update(UUID sellerId, Seller details) {
        Seller seller = sellerRepository.findById(sellerId).get();
        seller.setName(details.getName());
        seller.setAddress(details.getAddress());

        return sellerRepository.save(seller);
    }

    public Boolean sellerExistsOperation(UUID id) {
        return sellerRepository.existsById(id);
    }
}
