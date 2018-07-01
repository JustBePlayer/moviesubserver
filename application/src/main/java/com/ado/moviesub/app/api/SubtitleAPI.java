package com.ado.moviesub.app.api;

import com.ado.moviesub.app.entity.movie.SubtitleParser;
import com.ado.moviesub.app.entity.movie.locator.SubtitleContent;
import com.ado.moviesub.app.entity.movie.locator.SubtitleLocator;
import com.ado.moviesub.app.entity.movie.Subtitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubtitleAPI {
  @Autowired
  @Qualifier("fileLocatorAlias")
  private SubtitleLocator fileLocator;

  public List<Subtitle> getTranslatedSubtitles(){
    List<SubtitleContent> subtitleContents = fileLocator.getAllSubtitles();
    return convertSubtitleContent(subtitleContents);
  }

  public List<Subtitle> getTranslatedSubtitles(String movieName){
    List<SubtitleContent> subtitleContents = fileLocator.getSubtitlesByMovieName(movieName);
    return convertSubtitleContent(subtitleContents);
  }

  public Subtitle getTranslatedSubtitle(String providerId){
    SubtitleContent subtitleContent = fileLocator.getSubtitleInfoById(providerId);
    return convertSubtitleContent(subtitleContent);
  }

  public Subtitle downloadSubtitle(String providerId){
    SubtitleContent subtitleContent = fileLocator.downloadSubtitleFile(providerId);
    return convertSubtitleContent(subtitleContent);
  }

  private Subtitle convertSubtitleContent(SubtitleContent content){
    SubtitleParser subtitleParser = new SubtitleParser();
    return subtitleParser.parse(content);
  }

  private List<Subtitle> convertSubtitleContent(Collection<SubtitleContent> contents){
    // @formatter:off
    return contents.stream()
        .map(this::convertSubtitleContent)
        .collect(Collectors.toList());
    // @formatter:on
  }
}
