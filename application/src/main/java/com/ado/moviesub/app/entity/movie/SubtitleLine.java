package com.ado.moviesub.app.entity.movie;

import javax.persistence.*;
import java.time.Duration;

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

  public SubtitleLine(Builder builder) {
    this.id = builder.id;
    this.sequenceNumber = builder.sequenceNumber;
    this.startTime = builder.startTime;
    this.endTime = builder.endTime;
    this.text = builder.text;
  }

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

  public static class Builder{
    private Long id;
    private int sequenceNumber;
    private Duration startTime;
    private Duration endTime;
    private String text;

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setSequenceNumber(int sequenceNumber) {
      this.sequenceNumber = sequenceNumber;
      return this;
    }

    public Builder setStartTime(Duration startTime) {
      this.startTime = startTime;
      return this;
    }

    public Builder setEndTime(Duration endTime) {
      this.endTime = endTime;
      return this;
    }

    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    public SubtitleLine build(){
      return new SubtitleLine(this);
    }
  }
}
