package io.github.dmitry404.kcoderdata.web;

import io.github.dmitry404.kcoderdata.domain.Exercise;
import io.github.dmitry404.kcoderdata.services.ExercisesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ExercisesHandler {
  private final ExercisesDataService dataService;

  @Autowired
  public ExercisesHandler(ExercisesDataService dataService) {
    this.dataService = dataService;
  }

  public Mono<ServerResponse> exercises(ServerRequest request) {
    int size = Integer.parseInt(
        request.queryParam("size").orElse("5")
    );
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(dataService.fetchExercises(size), Exercise.class);
  }
}
