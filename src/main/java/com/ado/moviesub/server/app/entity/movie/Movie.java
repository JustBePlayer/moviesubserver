package com.ado.moviesub.server.app.entity.movie;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "duration")
  private Duration duration;

  @Column(name = "description")
  private String description;

  @ElementCollection(targetClass = Genre.class)
  @CollectionTable(
      name = "movie_genre",
      joinColumns = @JoinColumn(name = "id")
  )
  @Column(name = "genre_id")
  private Set<Genre> genres;


}
