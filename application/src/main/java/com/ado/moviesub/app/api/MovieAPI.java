package com.ado.moviesub.app.api;

import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.exception.ResourceNotFoundException;
import com.ado.moviesub.app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieAPI {

  @Autowired
  private MovieRepository repository;

  public Movie persistMovie(Movie movie){
    return repository.save(movie);
  }

  public void update(Movie movie){
    Long movieId = movie.getId();

    if(!isMovieExist(movieId)){
      throw ResourceNotFoundException.forMovie(movieId);
    }

    repository.save(movie);
  }

  public List<Movie> getMovies(){
    return repository.findAll();
  }

  public Movie getMovie(Long id){
    return repository.findById(id).orElseThrow(() -> ResourceNotFoundException.forMovie(id));
  }

  public void removeMovie(Long id){
    repository.deleteById(id);
  }

  private boolean isMovieExist(Long movieId){
    return repository.findById(movieId).isPresent();
  }
}
