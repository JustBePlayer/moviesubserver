package com.ado.moviesub.app.entity.movie.locator;

import com.ado.moviesub.app.entity.movie.Subtitle;

import java.util.List;

public interface SubtitleLocator {
  String FILE_NAME_DELIMITER = "-";

  Subtitle locate(String fileName);

  List<Subtitle> getAllSubtitles();

  List<Subtitle> getSubtitlesByMovieName(String movieName);
}
