package com.ado.moviesub.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.facade.MovieManagementFacade;

@RestController
@RequestMapping("movies")
public class MovieController {

  @Autowired
  private MovieManagementFacade movieManagementFacade;

  @GetMapping
  public ResponseEntity<List<Movie>> getMovies() {
    List<Movie> movies = movieManagementFacade.getMovies();
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovie(@PathVariable("id") Long id){
    Movie movie = movieManagementFacade.getMovie(id);
    return ResponseEntity.ok(movie);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie){
    Movie updated = movieManagementFacade.updateMovie(movie);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id){
    movieManagementFacade.removeMovie(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
    Movie createdMovie = movieManagementFacade.createMovie(movie);
    return ResponseEntity.ok(createdMovie);
  }
}
