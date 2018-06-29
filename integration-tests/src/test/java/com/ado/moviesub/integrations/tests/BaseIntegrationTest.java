package com.ado.moviesub.integrations.tests;

import com.ado.moviesub.app.Application;
import com.ado.moviesub.app.Endpoint;
import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.entity.user.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(locations = "classpath:test.properties")
public abstract class BaseIntegrationTest {

	@Autowired
  protected TestRestTemplate restClient;

  protected ResponseEntity<List<User>> getUsers(){
    return getResources(Endpoint.USERS);
  }

  protected ResponseEntity<User> getUser(Long id){
    return getResource(Endpoint.USERS, id, User.class);
  }

  protected ResponseEntity<User> createUser(User user){
    return createResource(Endpoint.USERS, user, User.class);
  }

  protected ResponseEntity<List<Movie>> getMovies(){
    return getResources(Endpoint.MOVIES);
  }

  protected ResponseEntity<Movie> getMovie(Long id){
    return getResource(Endpoint.MOVIES, id, Movie.class);
  }

  protected ResponseEntity<Movie> createMovie(Movie user){
    return createResource(Endpoint.MOVIES, user, Movie.class);
  }

  private <T> ResponseEntity<T> getResource(Endpoint endpoint, Long resourceId, Class<T> resourceClass){
    return restClient.getForEntity(endpoint.toEndpointPath(), resourceClass, resourceId);
  }

  private  <T> ResponseEntity<List<T>> getResources(Endpoint endpoint) {
    return restClient.exchange(endpoint.toEndpointPath(), HttpMethod.GET, null, new ParameterizedTypeReference<List<T>>() {});
  }

  private <T> ResponseEntity<T> createResource(Endpoint endpoint, T resource, Class<T> resourceClass){
    return restClient.postForEntity(endpoint.toEndpointPath(), resource, resourceClass);
  }
}
