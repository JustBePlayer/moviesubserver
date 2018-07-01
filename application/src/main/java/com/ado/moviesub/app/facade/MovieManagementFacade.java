package com.ado.moviesub.app.facade;

import com.ado.moviesub.app.entity.movie.Subtitle;

import java.util.List;

public interface MovieManagementFacade extends UserCRUD, MovieCRUD {
  List<Subtitle> getSubtitles();

  List<Subtitle> getReadyMovieSubtitles(Long movieId);

  Subtitle getTranslatedSubtitle(String id);

  Subtitle getTranslatedSubtitleWithContent(String id);
}
