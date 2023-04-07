package com.example.graphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;



/**
 * @Created 07 04 2023 - 8:44 AM
 * @Author Hazeem Hassan
 */
@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    private String title;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
