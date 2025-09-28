package com.example.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class CustomerRoutesTest {
  @Autowired WebTestClient client;

  @Test void list_and_crud() {
    client.get().uri("/customers").exchange().expectStatus().isOk();

    var created = client.post().uri("/customers")
        .bodyValue(new com.example.customers.domain.Customer(null, "Linus", "linus@example.com"))
        .exchange()
        .expectStatus().isCreated()
        .expectBody(com.example.customers.domain.Customer.class)
        .returnResult().getResponseBody();

    assert created != null;

    client.get().uri("/customers/{id}", created.id())
        .exchange().expectStatus().isOk();

    client.put().uri("/customers/{id}", created.id())
        .bodyValue(new com.example.customers.domain.Customer(created.id(), "Torvalds", "linus@example.com"))
        .exchange().expectStatus().isOk();

    client.delete().uri("/customers/{id}", created.id())
        .exchange().expectStatus().isNoContent();
  }
}
