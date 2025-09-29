package io.biztech.catalog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.math.BigDecimal;

 @Table("catalog_items")
public record Item(
    @Id Long itemId,
    @Column("item_name") String itemName,
    @Column("unit_price") BigDecimal unitPrice,
    @Column("category") String category,
    @Column("stock_quantity") Integer stockQuantity
) {}
