package io.biztech.clients.controller;

import io.biztech.clients.model.Client;
import io.biztech.clients.repository.ClientRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

 @Component
public class ClientHandler {
  private final ClientRepository repository;
  
  public ClientHandler(ClientRepository repository) { 
    this.repository = repository; 
  }

  public Mono<ServerResponse> getAllClients(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(repository.findAll(), Client.class);
  }

  public Mono<ServerResponse> getClientById(ServerRequest request) {
    var clientId = Long.parseLong(request.pathVariable("id"));
    return repository.findById(clientId)
        .flatMap(client -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(client))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> createClient(ServerRequest request) {
    return request.bodyToMono(Client.class)
        .flatMap(repository::save)
        .flatMap(savedClient -> ServerResponse
            .created(request.uri().resolve("/" + savedClient.clientId()))
            .body(fromValue(savedClient)));
  }

  public Mono<ServerResponse> updateClient(ServerRequest request) {
    var clientId = Long.parseLong(request.pathVariable("id"));
    return repository.existsById(clientId)
        .flatMap(exists -> exists ? 
            request.bodyToMono(Client.class)
                .map(client -> new Client(clientId, client.fullName(), client.emailAddress(), client.phoneNumber()))
                .flatMap(repository::save)
                .flatMap(updated -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updated))
            : ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> deleteClient(ServerRequest request) {
    var clientId = Long.parseLong(request.pathVariable("id"));
    return repository.deleteById(clientId)
        .then(ServerResponse.noContent().build());
  }
}
