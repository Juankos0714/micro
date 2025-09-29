package io.biztech.catalog.service;

import io.biztech.catalog.entity.Item;
import io.biztech.catalog.dao.ItemRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

 @Component
public class CatalogHandler {
  private final ItemRepository itemRepo;
  
  public CatalogHandler(ItemRepository itemRepo) {
    this.itemRepo = itemRepo;
  }

  public Mono<ServerResponse> listAllItems(ServerRequest req) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(itemRepo.findAll(), Item.class);
  }

  public Mono<ServerResponse> getItemById(ServerRequest req) {
    var itemId = Long.parseLong(req.pathVariable("id"));
    return itemRepo.findById(itemId)
        .flatMap(item -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(item))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> addNewItem(ServerRequest req) {
    return req.bodyToMono(Item.class)
        .flatMap(itemRepo::save)
        .flatMap(savedItem -> ServerResponse
            .created(req.uri().resolve("/" + savedItem.itemId()))
            .body(fromValue(savedItem)));
  }

  public Mono<ServerResponse> updateItem(ServerRequest req) {
    var itemId = Long.parseLong(req.pathVariable("id"));
    return itemRepo.existsById(itemId)
        .flatMap(exists -> exists ?
            req.bodyToMono(Item.class)
                .map(item -> new Item(itemId, item.itemName(), item.unitPrice(), item.category(), item.stockQuantity()))
                .flatMap(itemRepo::save)
                .flatMap(updated -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updated))
            : ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> removeItem(ServerRequest req) {
    var itemId = Long.parseLong(req.pathVariable("id"));
    return itemRepo.deleteById(itemId)
        .then(ServerResponse.noContent().build());
  }
}
