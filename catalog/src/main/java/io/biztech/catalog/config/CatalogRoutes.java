package io.biztech.catalog.config;

import io.biztech.catalog.service.CatalogHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

 @Configuration
public class CatalogRoutes {
  
  @Bean
  RouterFunction<ServerResponse> catalogRouter(CatalogHandler handler) {
    return RouterFunctions
      .route(GET("/api/catalog/items"), handler::listAllItems)
      .andRoute(GET("/api/catalog/items/{id}"), handler::getItemById)
      .andRoute(POST("/api/catalog/items"), handler::addNewItem)
      .andRoute(PUT("/api/catalog/items/{id}"), handler::updateItem)
      .andRoute(DELETE("/api/catalog/items/{id}"), handler::removeItem);
  }
}
