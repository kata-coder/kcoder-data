package io.github.dmitry404.kcoderdata.repositories;

import io.github.dmitry404.kcoderdata.domain.Exercise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface ExercisesRepository extends ReactiveMongoRepository<Exercise, String> {
}

