package io.github.dmitry404.kcoderdata.controllers;

import io.github.dmitry404.kcoderdata.domain.Exercise;
import io.github.dmitry404.kcoderdata.repositories.ExercisesRepository;
import io.github.dmitry404.kcoderdata.services.ExercisesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/exercises")
public class ExercisesDataController {
  private final ExercisesDataService dataService;

  @Autowired
  public ExercisesDataController(ExercisesDataService dataService) {
    this.dataService = dataService;
  }

  @GetMapping
  public Flux<Exercise> getAllExercises(@RequestParam(name = "size", defaultValue = "5") int size) {
    return dataService.fetchExercises(size);
  }
}
