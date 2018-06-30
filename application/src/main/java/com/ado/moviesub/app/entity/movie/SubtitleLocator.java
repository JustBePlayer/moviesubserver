package com.ado.moviesub.app.entity.movie;

import java.util.List;

public interface SubtitleLocator {
  Subtitle locate(String fileName);

  List<Subtitle> getAllSubtitles();

  List<Subtitle> getSubtitlesByMovieName(String movieName);
}
