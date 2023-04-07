package com.example.graphql.controller;

import com.example.graphql.model.Product;
import com.example.graphql.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * @Created 07 04 2023 - 9:05 AM
 * @Author Hazeem Hassan
 */
@RestController
@RequestMapping("/restApi/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
    public List<Product> readAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/getAProduct/{productId}")
    public ResponseEntity<Product> getAProduct(@PathVariable UUID productId) {
        try {
            Product product = productService.getById(productId);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable(value = "productId") @RequestBody UUID productId) {
        Boolean productExists = productService.productExistsOperation(productId);
        if (productExists) {
            Product product = productService.getById(productId);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to fetch product data because there's no product exist in database against productId = " + productId, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/addNewProduct", method = RequestMethod.POST)
    public Product addNewProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @RequestMapping(value = "/updateOperation/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOperation(@PathVariable(value = "productId") UUID productId, @RequestBody Product details) {
        Boolean productExists = productService.productExistsOperation(productId);
        if (productExists) {
            return new ResponseEntity<Product>(productService.update(productId, details), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to update product because there's no product exist in database springbootDB against productId = " + productId, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/deleteOperation/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOperation(@PathVariable(value = "productId") @RequestBody UUID productID) {
        Boolean productExists = productService.productExistsOperation(productID);
        if (productExists) {
            productService.remove(productID);
            return new ResponseEntity<String>("User Successfully Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to delete product because there's no product exist in database springbootDB against productId = " + productID, HttpStatus.NOT_FOUND);
        }
    }
}
