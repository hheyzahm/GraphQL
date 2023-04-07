package com.example.graphql.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import java.util.UUID;

/**
 * @Created 07 04 2023 - 8:38 AM
 * @Author Hazeem Hassan
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2"
    )
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
}
