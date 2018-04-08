package io.github.dmitry404.kcoderdata.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ExercisesRouter {
  @Bean
  public RouterFunction<ServerResponse> route(ExercisesHandler handler) {
    return RouterFunctions.route(
        GET("/exercises").and(
            accept(MediaType.APPLICATION_JSON_UTF8)
        ), handler::exercises);
  }
}
