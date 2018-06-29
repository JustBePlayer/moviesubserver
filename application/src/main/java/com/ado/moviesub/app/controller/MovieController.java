package com.ado.moviesub.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.facade.MovieManagementFacade;

@RestController
@RequestMapping("movies")
public class MovieController {

  @Autowired
  private MovieManagementFacade movieManagementFacade;

  @GetMapping
  public ResponseEntity<List<Movie>> getMovies(){
    List<Movie> movies = movieManagementFacade.getMovies();
    return ResponseEntity.ok(movies);
  }
}
