package io.biztech.sales.handler;

import io.biztech.sales.domain.Purchase;
import io.biztech.sales.repository.PurchaseRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

 @Component
public class SalesHandler {
  private final PurchaseRepository purchaseRepo;
  
  public SalesHandler(PurchaseRepository purchaseRepo) {
    this.purchaseRepo = purchaseRepo;
  }

  public Mono<ServerResponse> getAllPurchases(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(purchaseRepo.findAll(), Purchase.class);
  }

  public Mono<ServerResponse> getPurchaseById(ServerRequest request) {
    var purchaseId = Long.parseLong(request.pathVariable("id"));
    return purchaseRepo.findById(purchaseId)
        .flatMap(purchase -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(purchase))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> createPurchase(ServerRequest request) {
    return request.bodyToMono(Purchase.class)
        .map(p -> new Purchase(null, p.clientId(), p.itemId(), p.quantity(), 
                              LocalDateTime.now(), "PENDING"))
        .flatMap(purchaseRepo::save)
        .flatMap(saved -> ServerResponse
            .created(request.uri().resolve("/" + saved.purchaseId()))
            .body(fromValue(saved)));
  }

  public Mono<ServerResponse> updatePurchase(ServerRequest request) {
    var purchaseId = Long.parseLong(request.pathVariable("id"));
    return purchaseRepo.existsById(purchaseId)
        .flatMap(exists -> exists ?
            request.bodyToMono(Purchase.class)
                .map(p -> new Purchase(purchaseId, p.clientId(), p.itemId(), 
                                     p.quantity(), p.purchaseDate(), p.status()))
                .flatMap(purchaseRepo::save)
                .flatMap(updated -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updated))
            : ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> deletePurchase(ServerRequest request) {
    var purchaseId = Long.parseLong(request.pathVariable("id"));
    return purchaseRepo.deleteById(purchaseId)
        .then(ServerResponse.noContent().build());
  }
}
