package io.biztech.clients;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 @AutoConfigureWebTestClient @ActiveProfiles("test")
class ClientRoutesTest {
  @Autowired WebTestClient webClient;

  @Test 
  void testCompleteClientCrud() {
    // Test GET all clients
    webClient.get()
        .uri("/api/clients")
        .exchange()
        .expectStatus().isOk();

    // Test POST new client
    var newClient = new io.biztech.clients.model.Client(
        null, 
        "Juan Pérez", 
        "juan.perez @test.com", 
        "+57-300-1111111"
    );
    
    var createdClient = webClient.post()
        .uri("/api/clients")
        .bodyValue(newClient)
        .exchange()
        .expectStatus().isCreated()
        .expectBody(io.biztech.clients.model.Client.class)
        .returnResult().getResponseBody();

    assert createdClient != null;

    // Test GET client by ID
    webClient.get()
        .uri("/api/clients/{id}", createdClient.clientId())
        .exchange()
        .expectStatus().isOk();

    // Test PUT update client
    var updatedClient = new io.biztech.clients.model.Client(
        createdClient.clientId(), 
        "Juan Carlos Pérez", 
        "juan.perez @test.com", 
        "+57-301-2222222"
    );
    
    webClient.put()
        .uri("/api/clients/{id}", createdClient.clientId())
        .bodyValue(updatedClient)
        .exchange()
        .expectStatus().isOk();

    // Test DELETE client
    webClient.delete()
        .uri("/api/clients/{id}", createdClient.clientId())
        .exchange()
        .expectStatus().isNoContent();
  }
}
