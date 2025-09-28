package com.example.orders.domain;
import org.springframework.data.annotation.Id; import org.springframework.data.relational.core.mapping.Table; import org.springframework.data.relational.core.mapping.Column;

@Table("orders")
public record Order(@Id Long id, @Column("customer_id") Long customerId, @Column("product_id") Long productId, @Column("qty") Integer qty) {}
