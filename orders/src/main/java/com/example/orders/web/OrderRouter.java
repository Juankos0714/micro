package com.example.orders.web;
import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.web.reactive.function.server.RouterFunction; import org.springframework.web.reactive.function.server.RouterFunctions; import org.springframework.web.reactive.function.server.ServerResponse; import static org.springframework.web.reactive.function.server.RequestPredicates.*;
@Configuration public class OrderRouter {
  @Bean RouterFunction<ServerResponse> routes(OrderHandler h){
    return RouterFunctions.route(GET("/orders"),h::all)
      .andRoute(GET("/orders/{id}"),h::byId)
      .andRoute(POST("/orders"),h::create)
      .andRoute(PUT("/orders/{id}"),h::update)
      .andRoute(DELETE("/orders/{id}"),h::delete);
  }
}
