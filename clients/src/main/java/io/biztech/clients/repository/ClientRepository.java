package io.biztech.clients.repository;

import io.biztech.clients.model.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientRepository extends ReactiveCrudRepository<Client, Long> { }
