package com.ado.moviesub.app.api;

import com.ado.moviesub.app.entity.movie.locator.SubtitleLocator;
import com.ado.moviesub.app.entity.movie.Subtitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtitleAPI {
  @Autowired
  @Qualifier("fileLocatorAlias")
  private SubtitleLocator fileLocator;

  public List<Subtitle> getTranslatedSubtitles(){
    return fileLocator.getAllSubtitles();
  }

  public List<Subtitle> getTranslatedSubtitles(String movieName){
    return fileLocator.getSubtitlesByMovieName(movieName);
  }

}
