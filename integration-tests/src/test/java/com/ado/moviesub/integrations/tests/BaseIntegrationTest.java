package com.ado.moviesub.integrations.tests;

import com.ado.moviesub.app.Application;
import com.ado.moviesub.app.Endpoint;
import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.entity.user.User;
import com.ado.moviesub.integrations.tests.util.ServerResponse;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
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

  protected ServerResponse<List<User>> getUsers(){
    return getResources(Endpoint.USERS, User.class);
  }

  protected ServerResponse<User> getUser(Long id){
    return getResource(Endpoint.USERS, id, User.class);
  }

  protected ServerResponse<User> createUser(User user){
    return createResource(Endpoint.USERS, user, User.class);
  }

  protected ServerResponse<User> updateUser (User user){
    return updateResource(Endpoint.USERS, user, user.getId(), User.class);
  }

  protected ServerResponse<List<Movie>> getMovies(){
    return getResources(Endpoint.MOVIES, Movie.class);
  }

  protected ServerResponse<Movie> getMovie(Long id){
    return getResource(Endpoint.MOVIES, id, Movie.class);
  }

  protected ServerResponse<Movie> createMovie(Movie user){
    return createResource(Endpoint.MOVIES, user, Movie.class);
  }

  protected ServerResponse<Movie> updateMovie (Movie movie){
    return updateResource(Endpoint.MOVIES, movie, movie.getId(), Movie.class);
  }

  private <T> ServerResponse<T> getResource(Endpoint endpoint, Long resourceId, Class<T> resourceClass){
    ResponseEntity<String> responseEntity = restClient.getForEntity(endpoint.toEndpointPath(resourceId), String.class);
    return ServerResponse.ofObject(responseEntity, resourceClass);
  }

  private <T> ServerResponse<List<T>> getResources(Endpoint endpoint, Class<T> resourceClass) {
    ResponseEntity<String> responseEntity = restClient.exchange(endpoint.toEndpointPath(), HttpMethod.GET, null, String.class);
    return ServerResponse.ofCollection(responseEntity, resourceClass);
  }

  private <T> ServerResponse<T> createResource(Endpoint endpoint, T resource, Class<T> resourceClass){
    ResponseEntity<String> responseEntity  = restClient.postForEntity(endpoint.toEndpointPath(), resource, String.class);
    return ServerResponse.ofObject(responseEntity, resourceClass);
  }

  private <T> ServerResponse<T> updateResource(Endpoint endpoint, T resource, Long id, Class<T> resourceClass){
    ResponseEntity<String> responseEntity = restClient.exchange(endpoint.toEndpointPath(id), HttpMethod.PUT, new HttpEntity<>(resource), String.class);
    return ServerResponse.ofObject(responseEntity, resourceClass);
  }

  private ServerResponse<Object> removeResource(Endpoint endpoint, Long resourceId){
    ResponseEntity<String> responseEntity = restClient.exchange(endpoint.toEndpointPath(resourceId), HttpMethod.DELETE, null, String.class);
    return ServerResponse.ofObject(responseEntity, Object.class);
  }

}
