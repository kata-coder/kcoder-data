package io.github.dmitry404.kcoderdata;

import io.github.dmitry404.kcoderdata.domain.Exercise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExercisesDataTests {
  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void fetchExercisesTest_byDefaultItFive_andContainTitles() throws Exception {
    webTestClient
        .get()
        .uri("/exercises")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Exercise.class)
        .hasSize(5)
        .consumeWith(
            exercises -> {
              assertThat(exercises.getResponseBody())
                  .allSatisfy(ex -> assertThat(ex.getContent()).startsWith("###"));
            }
        );
  }

  @Test
  public void fetchExercisesTest_withSizeSpecified() throws Exception {
    webTestClient
        .get()
        .uri("/exercises?size=2")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Exercise.class)
        .hasSize(2);
  }
}
