package com.ado.moviesub.app.entity.movie;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Subtitle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "subtitle_type")
public class Subtitle {

  @Transient
  public static final String FILE_PATTERN = "MOVIE_NAME-LANGUAGE-VERSION";

  @Transient
  public static final String DEFAULT_VERSION = "00000000-0000-0000-0000-000000000000";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "version", nullable = false)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private String version;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "language", nullable = false)
  private Language language;

  @OneToMany(mappedBy = "subtitle")
  private List<SubtitleLine> lines;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;

  public Subtitle(Builder builder) {
    this.id = builder.id;
    this.language = builder.language;
    this.version = builder.version;
    this.lines = builder.lines;
    this.movie = builder.movie;
  }

  public Long getId() {
    return id;
  }

  public String getVersion() {
    return version;
  }

  public Language getLanguage() {
    return language;
  }

  public List<SubtitleLine> getLines() {
    return lines;
  }

  public static class Builder{
    private Long id;
    private String version;

    private Language language;
    private List<SubtitleLine> lines;
    private Movie movie;

    public Builder (){
      this.lines = new ArrayList<>();
    }

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setVersion(String version) {
      this.version = version;
      return this;
    }

    public Builder setLanguage(Language language) {
      this.language = language;
      return this;
    }

    public Builder setSubtitleLines(List<SubtitleLine> subtitleLines) {
      this.lines = subtitleLines;
      return this;
    }

    public Builder setMovie(Movie movie) {
      this.movie = movie;
      return this;
    }

    public Subtitle build(){
      return new Subtitle(this);
    }
  }
}
