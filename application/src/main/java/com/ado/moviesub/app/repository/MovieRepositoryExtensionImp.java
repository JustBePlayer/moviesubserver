package com.ado.moviesub.app.repository;

import com.ado.moviesub.app.entity.movie.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MovieRepositoryExtensionImp implements MovieRepositoryExtension {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Movie> getMoviesByName(String movieName) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Movie> movieCriteriaQuery = criteriaBuilder.createQuery(Movie.class);

    Root<Movie> root = movieCriteriaQuery.from(Movie.class);

    movieCriteriaQuery.where(criteriaBuilder.equal(root.get("name"), movieName));

    Query query = entityManager.createQuery(movieCriteriaQuery);
    return query.getResultList();
  }
}
