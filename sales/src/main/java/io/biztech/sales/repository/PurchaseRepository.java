package io.biztech.sales.repository;

import io.biztech.sales.domain.Purchase;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PurchaseRepository extends ReactiveCrudRepository<Purchase, Long> { }
