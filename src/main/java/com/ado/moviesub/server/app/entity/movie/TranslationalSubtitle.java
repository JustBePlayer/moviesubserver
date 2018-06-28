package com.ado.moviesub.server.app.entity.movie;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "TranslationalSubtitle")
@DiscriminatorValue("translational")
public class TranslationalSubtitle {

  @OneToMany(mappedBy = "subtitle")
  private List<SubtitleLine> translatedLines;


}

