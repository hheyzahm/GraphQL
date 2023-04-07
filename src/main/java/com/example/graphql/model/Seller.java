package com.example.graphql.model;

import jakarta.persistence.Entity;
import lombok.Data;

/**
 * @Created 07 04 2023 - 8:46 AM
 * @Author Hazeem Hassan
 */
@Data
@Entity
public class Seller  extends BaseEntity{
    private String name;
    private String address;
}
