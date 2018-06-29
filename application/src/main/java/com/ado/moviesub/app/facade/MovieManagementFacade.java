package com.ado.moviesub.app.facade;

import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.entity.user.User;

import java.util.List;

public interface MovieManagementFacade {
  User registerUser(User user);

  void removeUser(User user);

  List<User> getUsers();

  List<Movie> getMovies();
}
