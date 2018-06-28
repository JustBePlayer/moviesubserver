package com.ado.moviesub.server.app.entity.movie;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subtitle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "subtitle_type")
public class Subtitle {

  @Transient
  private static final String FILE_PATTERN = "MOVIE_NAME-LANGUAGE-VERSION";

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
}
