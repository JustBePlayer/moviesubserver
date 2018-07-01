package com.ado.moviesub.integrations.tests;

import com.ado.moviesub.app.Application;
import com.ado.moviesub.app.Endpoint;
import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.entity.movie.locator.google.ProviderConfigProperties;
import com.ado.moviesub.app.entity.user.User;
import com.ado.moviesub.integrations.tests.util.ServerResponse;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(locations = {"classpath:test.properties", "classpath:fileProvider/google.properties"})
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

	@Autowired
  protected TestRestTemplate restClient;

  @Configuration
  @Import(Application.class)
  @Profile("test")
  static class TestConfig{
    @Bean
    public ProviderConfigProperties getProviderConfigProperties(){
      ProviderConfigProperties properties = new ProviderConfigProperties();
      properties.setAccessType("offffffline");
      properties.setClientSecretDir("sometsfgfd");
      properties.setCredentialsFolder("sometsfgfd");
      properties.setUserId("sometsfgfd");
      return properties;
    }
  }

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

  protected ServerResponse<Movie> createMovie(Movie movie){
    return createResource(Endpoint.MOVIES, movie, Movie.class);
  }

  protected ServerResponse<Movie> updateMovie (Movie movie){
    return updateResource(Endpoint.MOVIES, movie, movie.getId(), Movie.class);
  }

  protected <T> ServerResponse<T> getResource(Endpoint endpoint, Object resourceId, Class<T> resourceClass){
    return getResource(endpoint.toEndpointPath(resourceId.toString()), resourceClass);
  }

  protected <T> ServerResponse<T> getResource(String endpoint, Class<T> resourceClass){
    ResponseEntity<String> responseEntity = restClient.getForEntity(endpoint, String.class);
    return ServerResponse.ofObject(responseEntity, resourceClass);
  }

  protected <T> ServerResponse<List<T>> getResources(Endpoint endpoint, Class<T> resourceClass) {
    ResponseEntity<String> responseEntity = restClient.exchange(endpoint.toEndpointPath(), HttpMethod.GET, null, String.class);
    return ServerResponse.ofCollection(responseEntity, resourceClass);
  }

  protected <T> ServerResponse<T> createResource(Endpoint endpoint, T resource, Class<T> resourceClass){
    ResponseEntity<String> responseEntity  = restClient.postForEntity(endpoint.toEndpointPath(), resource, String.class);
    return ServerResponse.ofObject(responseEntity, resourceClass);
  }

  protected  <T> ServerResponse<T> updateResource(Endpoint endpoint, T resource, Long id, Class<T> resourceClass){
    ResponseEntity<String> responseEntity = restClient.exchange(endpoint.toEndpointPath(id), HttpMethod.PUT, new HttpEntity<>(resource), String.class);
    return ServerResponse.ofObject(responseEntity, resourceClass);
  }

  private ServerResponse<Object> removeResource(Endpoint endpoint, Long resourceId){
    ResponseEntity<String> responseEntity = restClient.exchange(endpoint.toEndpointPath(resourceId), HttpMethod.DELETE, null, String.class);
    return ServerResponse.ofObject(responseEntity, Object.class);
  }

}
