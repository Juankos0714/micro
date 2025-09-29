package io.biztech.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.math.BigDecimal;

 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 @AutoConfigureWebTestClient @ActiveProfiles("test")
class CatalogRoutesTest {
  @Autowired WebTestClient webClient;

  @Test 
  void testCatalogOperations() {
    // Test listing items
    webClient.get()
        .uri("/api/catalog/items")
        .exchange()
        .expectStatus().isOk();
        
    // Test creating new item
    var newItem = new io.biztech.catalog.entity.Item(
        null,
        "Nuevo Artículo",
        new BigDecimal("299.99"),
        "Electrónicos",
        50
    );
    
    webClient.post()
        .uri("/api/catalog/items")
        .bodyValue(newItem)
        .exchange()
        .expectStatus().isCreated();
  }
}
