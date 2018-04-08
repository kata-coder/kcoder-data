package io.github.dmitry404.kcoderdata.bootstrap;

import io.github.dmitry404.kcoderdata.domain.Exercise;
import io.github.dmitry404.kcoderdata.repositories.ExercisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class DataPopulateCLR implements CommandLineRunner {
  private final ExercisesRepository exercisesRepository;
  private final ResourceLoader resourceLoader;

  @Autowired
  public DataPopulateCLR(ExercisesRepository exercisesRepository, ResourceLoader resourceLoader) {
    this.exercisesRepository = exercisesRepository;
    this.resourceLoader = resourceLoader;
  }

  @Override
  public void run(String... args) throws Exception {
    Resource resource = resourceLoader.getResource("classpath:katas.dat");
    InputStream resourceInputStream = resource.getInputStream();
    try (Scanner scanner = new Scanner(resourceInputStream, StandardCharsets.UTF_8.name())) {
      List<String> katas = new ArrayList<>();
      scanner.useDelimiter("-----").forEachRemaining(kata -> katas.add(kata.trim()));

      exercisesRepository.deleteAll()
          .thenMany(
              Flux.fromIterable(katas)
                  .map(kata -> new Exercise(UUID.randomUUID().toString(), kata))
                  .flatMap(exercisesRepository::save)
          )
          .subscribe(
              null,
              System.err::println,
              () -> System.out.println("Exercise data population has been finished")
          );
    }
  }
}
