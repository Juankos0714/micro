package io.biztech.sales.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.time.LocalDateTime;

 @Table("purchases")
public record Purchase(
    @Id Long purchaseId,
    @Column("client_id") Long clientId,
    @Column("item_id") Long itemId,
    @Column("quantity") Integer quantity,
    @Column("purchase_date") LocalDateTime purchaseDate,
    @Column("status") String status
) {}
