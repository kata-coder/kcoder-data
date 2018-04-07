package io.github.dmitry404.kcoderdata.services;

import io.github.dmitry404.kcoderdata.domain.Exercise;
import io.github.dmitry404.kcoderdata.repositories.ExercisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ExercisesDataService {
  private final ExercisesRepository exercisesRepository;

  @Autowired
  public ExercisesDataService(ExercisesRepository exercisesRepository) {
    this.exercisesRepository = exercisesRepository;
  }

  public Flux<Exercise> fetchExercises(int size) {
    return exercisesRepository.findAll().take(size);
  }
}
