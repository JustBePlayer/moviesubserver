package com.ado.moviesub.server.app.entity.movie;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Table(name = "subtitle_line")
public class SubtitleLine {

  protected SubtitleLine(){

  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sequence_number")
  private int sequenceNumber;

  @Column(name = "start_time")
  private Duration startTime;

  @Column(name = "end_time")
  private Duration endTime;

  @Column(name = "text")
  private String text;

  @ManyToOne
  @JoinColumn(name = "sub_id")
  private Subtitle subtitle;

  public Subtitle getSubtitle() {
    return subtitle;
  }

  public Long getId() {
    return id;
  }

  public int getSequenceNumber() {
    return sequenceNumber;
  }

  public Duration getStartTime() {
    return startTime;
  }

  public Duration getEndTime() {
    return endTime;
  }

  public String getText() {
    return text;
  }
}
