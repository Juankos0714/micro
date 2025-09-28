package com.example.orders.repo;
import com.example.orders.domain.Order; import org.springframework.data.repository.reactive.ReactiveCrudRepository;
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> { }
