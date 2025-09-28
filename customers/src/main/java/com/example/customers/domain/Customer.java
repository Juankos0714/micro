package com.example.customers.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("customers")
public record Customer(
    @Id Long id,
    @Column("name") String name,
    @Column("email") String email
) {}
