package io.biztech.sales.config;

import io.biztech.sales.handler.SalesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

 @Configuration
public class SalesRoutes {
  
  @Bean
  RouterFunction<ServerResponse> salesRouter(SalesHandler handler) {
    return RouterFunctions
      .route(GET("/api/sales/purchases"), handler::getAllPurchases)
      .andRoute(GET("/api/sales/purchases/{id}"), handler::getPurchaseById)
      .andRoute(POST("/api/sales/purchases"), handler::createPurchase)
      .andRoute(PUT("/api/sales/purchases/{id}"), handler::updatePurchase)
      .andRoute(DELETE("/api/sales/purchases/{id}"), handler::deletePurchase);
  }
}
