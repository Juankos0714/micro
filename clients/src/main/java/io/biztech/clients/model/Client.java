package io.biztech.clients.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

 @Table("clients")
public record Client(
    @Id Long clientId,
    @Column("full_name") String fullName,
    @Column("email_address") String emailAddress,
    @Column("phone_number") String phoneNumber
) {}
