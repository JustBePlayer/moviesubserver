package com.ado.moviesub.server.app.entity.movie;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subtitle")
public class Subtitle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "language")
  private Language language;

  @OneToMany(mappedBy = "subtitle")
  private List<SubtitleLine> lines;


}
