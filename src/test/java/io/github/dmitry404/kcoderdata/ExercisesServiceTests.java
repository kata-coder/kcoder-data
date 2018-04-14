package io.github.dmitry404.kcoderdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.dmitry404.kcoderdata.domain.Exercise;
import io.github.dmitry404.kcoderdata.repositories.ExercisesRepository;
import io.github.dmitry404.kcoderdata.services.ExercisesDataService;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ExercisesServiceTests {
  private ExercisesDataService exercisesDataService;

  @MockBean
  ExercisesRepository repository;

  @BeforeEach
  void setUp() {
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 3, 7 })
  void fetchDataAccordingToSizeParam(int size) {
    Flux<Exercise> allExercises = Flux.just(0).repeat(10).map(id -> new Exercise("", ""));
    when(repository.findAll()).thenReturn(allExercises);

    exercisesDataService = new ExercisesDataService(repository);

    Flux<Exercise> flux = exercisesDataService.fetchExercises(size);

    StepVerifier.create(flux)
        .expectNextCount(size)
        .verifyComplete();
  }

}
