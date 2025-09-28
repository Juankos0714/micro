package com.example.customers.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CustomerRouter {
  @Bean
  RouterFunction<ServerResponse> routes(CustomerHandler h) {
    return RouterFunctions
      .route(GET("/customers"), h::all)
      .andRoute(GET("/customers/{id}"), h::byId)
      .andRoute(POST("/customers"), h::create)
      .andRoute(PUT("/customers/{id}"), h::update)
      .andRoute(DELETE("/customers/{id}"), h::delete);
  }
}
