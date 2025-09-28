package com.example.customers.web;

import com.example.customers.domain.Customer;
import com.example.customers.repo.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class CustomerHandler {
  private final CustomerRepository repo;
  public CustomerHandler(CustomerRepository repo) { this.repo = repo; }

  public Mono<ServerResponse> all(ServerRequest req) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(repo.findAll(), Customer.class);
  }

  public Mono<ServerResponse> byId(ServerRequest req) {
    var id = Long.parseLong(req.pathVariable("id"));
    return repo.findById(id)
        .flatMap(c -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(c))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> create(ServerRequest req) {
    return req.bodyToMono(Customer.class)
        .flatMap(repo::save)
        .flatMap(saved -> ServerResponse.created(req.uri().resolve("/" + saved.id())).body(fromValue(saved)));
  }

  public Mono<ServerResponse> update(ServerRequest req) {
    var id = Long.parseLong(req.pathVariable("id"));
    return repo.existsById(id)
        .flatMap(exists -> exists ? req.bodyToMono(Customer.class)
            .map(c -> new Customer(id, c.name(), c.email()))
            .flatMap(repo::save)
            .flatMap(saved -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(saved))
            : ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> delete(ServerRequest req) {
    var id = Long.parseLong(req.pathVariable("id"));
    return repo.deleteById(id).then(ServerResponse.noContent().build());
  }
}
