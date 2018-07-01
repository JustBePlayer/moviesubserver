package com.ado.moviesub.app.controller;

import com.ado.moviesub.app.entity.movie.Subtitle;
import com.ado.moviesub.app.facade.MovieManagementFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subtitles")
public class SubtitleController {

  @Autowired
  private MovieManagementFacade movieManagementFacade;


  @GetMapping("/translated/{id}")
  public ResponseEntity<Subtitle> getSubtitleById(@PathVariable("id") String id){
    Subtitle subtitle = movieManagementFacade.getTranslatedSubtitle(id);
    return ResponseEntity.ok(subtitle);
  }

  @GetMapping("/translated/{id}/full")
  public ResponseEntity<Subtitle> getSubtitleByIdWiltLines(@PathVariable("id") String id){
    Subtitle subtitle = movieManagementFacade.getTranslatedSubtitleWithContent(id);
    return ResponseEntity.ok(subtitle);
  }

  @GetMapping("/translated")
  public ResponseEntity<List<Subtitle>> getSubtitleByMovieId(@RequestParam(value = "movieId", required = false) Long movieId){
    List<Subtitle> subtitles;

    if(movieId == null){
      subtitles = movieManagementFacade.getSubtitles();
    } else {
      subtitles = movieManagementFacade.getReadyMovieSubtitles(movieId);
    }

    return ResponseEntity.ok(subtitles);
  }
}
