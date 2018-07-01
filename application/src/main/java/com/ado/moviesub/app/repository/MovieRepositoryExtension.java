package com.ado.moviesub.app.repository;

import com.ado.moviesub.app.entity.movie.Movie;

import java.util.List;

public interface MovieRepositoryExtension {
  public List<Movie> getMoviesByName(String movieName);
}
