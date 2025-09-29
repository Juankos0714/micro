package io.biztech.clients.config;

import io.biztech.clients.controller.ClientHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

 @Configuration
public class ClientRoutes {
  
  @Bean
  RouterFunction<ServerResponse> clientRouter(ClientHandler handler) {
    return RouterFunctions
      .route(GET("/api/clients"), handler::getAllClients)
      .andRoute(GET("/api/clients/{id}"), handler::getClientById)
      .andRoute(POST("/api/clients"), handler::createClient)
      .andRoute(PUT("/api/clients/{id}"), handler::updateClient)
      .andRoute(DELETE("/api/clients/{id}"), handler::deleteClient);
  }
}
