package com.ado.moviesub.app.facade;

import com.ado.moviesub.app.api.MovieAPI;
import com.ado.moviesub.app.api.UserAPI;
import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultMovieManagementFacade implements MovieManagementFacade {

  @Autowired
  private UserAPI userAPI;

  @Autowired
  private MovieAPI movieAPI;

  @Override
  public User registerUser(User user) {
    return userAPI.persistUser(user);
  }

  @Override
  public void removeUser(Long id) {
    userAPI.removeUser(id);
  }

  @Override
  public List<User> getUsers() {
    return userAPI.getUsers();
  }

  @Override
  public User updateUser(User user) {
    userAPI.updateUser(user);
    return user;
  }

  @Override
  public User getUser(Long userId) {
    return userAPI.getUser(userId);
  }

  @Override
  public List<Movie> getMovies() {
    return movieAPI.getMovies();
  }

  @Override
  public void removeMovie(Long id) {
    movieAPI.removeMovie(id);
  }

  @Override
  public Movie createMovie(Movie movie) {
    return movieAPI.persistMovie(movie);
  }

  @Override
  public Movie getMovie(Long id) {
    return movieAPI.getMovie(id);
  }

  @Override
  public Movie updateMovie(Movie movie) {
    movieAPI.update(movie);
    return movie;
  }
}
