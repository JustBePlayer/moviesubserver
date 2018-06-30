package com.ado.moviesub.app.facade;

import com.ado.moviesub.app.entity.movie.Movie;

import java.util.List;

public interface MovieCRUD {
  List<Movie> getMovies();

  void removeMovie(Long id);

  Movie createMovie(Movie movie);

  Movie getMovie(Long id);

  Movie updateMovie(Movie movie);
}
