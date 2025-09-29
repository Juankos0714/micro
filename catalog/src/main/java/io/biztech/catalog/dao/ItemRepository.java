package io.biztech.catalog.dao;

import io.biztech.catalog.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> { }
