package com.ado.moviesub.app.entity.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Entity
@Table(name = "Movie")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

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

  @OneToMany(mappedBy = "movie")
  private Set<Subtitle> subtitles;

  protected Movie(){

  }

  private Movie(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("duration") Duration duration,
      @JsonProperty("description") String description) {
    this.id = id;
    this.name = name;
    this.duration = duration;
    this.description = description;
  }

  private Movie(Builder builder){
    this.id = builder.id;
    this.name = builder.name;
    this.description = builder.description;
    this.duration = builder.duration;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Duration getDuration() {
    return duration;
  }

  public String getDescription() {
    return description;
  }

  public Set<Genre> getGenres() {
    return genres;
  }

  public Set<Subtitle> getSubtitles() {
    return subtitles;
  }

  public static class Builder{
    private Integer id;
    private String name;
    private Duration duration;
    private String description;

    public Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setDuration(Duration duration) {
      this.duration = duration;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Movie build(){
      return new Movie(this);
    }
  }
}
