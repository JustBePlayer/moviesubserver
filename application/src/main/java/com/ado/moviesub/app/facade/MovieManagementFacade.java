package com.ado.moviesub.app.facade;

import com.ado.moviesub.app.entity.movie.Subtitle;

import java.util.List;

public interface MovieManagementFacade extends UserCRUD, MovieCRUD {
  public List<Subtitle> getSubtitles();
}
