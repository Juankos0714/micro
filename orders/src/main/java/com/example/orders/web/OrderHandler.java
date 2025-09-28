package com.example.orders.web;
import com.example.orders.domain.Order; import com.example.orders.repo.OrderRepository; import org.springframework.http.MediaType; import org.springframework.stereotype.Component; import org.springframework.web.reactive.function.server.ServerRequest; import org.springframework.web.reactive.function.server.ServerResponse; import reactor.core.publisher.Mono; import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class OrderHandler {
  private final OrderRepository repo; public OrderHandler(OrderRepository r){this.repo=r;}
  public Mono<ServerResponse> all(ServerRequest req){ return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repo.findAll(), Order.class);} 
  public Mono<ServerResponse> byId(ServerRequest req){ var id=Long.parseLong(req.pathVariable("id")); return repo.findById(id).flatMap(o->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(o)).switchIfEmpty(ServerResponse.notFound().build()); }
  public Mono<ServerResponse> create(ServerRequest req){ return req.bodyToMono(Order.class).flatMap(repo::save).flatMap(o->ServerResponse.created(req.uri().resolve("/"+o.id())).body(fromValue(o))); }
  public Mono<ServerResponse> update(ServerRequest req){ var id=Long.parseLong(req.pathVariable("id")); return repo.existsById(id).flatMap(exists-> exists ? req.bodyToMono(Order.class).map(o->new Order(id,o.customerId(),o.productId(),o.qty())).flatMap(repo::save).flatMap(saved->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(saved)) : ServerResponse.notFound().build()); }
  public Mono<ServerResponse> delete(ServerRequest req){ var id=Long.parseLong(req.pathVariable("id")); return repo.deleteById(id).then(ServerResponse.noContent().build()); }
}
