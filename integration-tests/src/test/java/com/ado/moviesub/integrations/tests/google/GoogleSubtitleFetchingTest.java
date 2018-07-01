package com.ado.moviesub.integrations.tests.google;

import static org.junit.Assert.*;

import com.ado.moviesub.app.entity.movie.Movie;
import com.ado.moviesub.app.entity.movie.Subtitle;
import com.ado.moviesub.integrations.tests.util.ServerResponse;

import com.ado.moviesub.integrations.tests.util.TestData;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

public class GoogleSubtitleFetchingTest extends GoogleIntegrationTest {

  @Test
  public void testGetAllSubtitles() throws IOException {
    ServerResponse<List<Subtitle>> response = getAllSubtitles();
    assertEquals(HttpStatus.OK, response.getStatusCode());

    List<Subtitle> subtitles = response.getResponseObject();
    assertFalse(subtitles.isEmpty());
  }

  @Test
  public void testGetSubtitleWithValidId() throws IOException {
    ServerResponse<Subtitle> response = getTranslatedSubtitleById(EXISTING_SUBTITLE_ID);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    Subtitle subtitle = response.getResponseObject();
    assertEquals(EXISTING_SUBTITLE_ID, subtitle.getProviderId());
  }

  @Test
  public void testGetSubtitleWithIWrongId() {
    ServerResponse<Subtitle> response = getTranslatedSubtitleById("something");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testGetSubtitleWithLines() throws IOException {
    ServerResponse<Subtitle> response = getTranslatedSubtitleWithLines(EXISTING_SUBTITLE_ID);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    Subtitle subtitle = response.getResponseObject();
    assertEquals(EXISTING_SUBTITLE_ID, subtitle.getProviderId());
    assertFalse(subtitle.getLines().isEmpty());
  }

  @Test
  public void testGetSubtitlesByMovieId() throws IOException {
    Movie movie = TestData.createSampleMovie("shrek");
    ServerResponse<Movie> createdMovieResponse = createMovie(movie);
    assertEquals(HttpStatus.OK, createdMovieResponse.getStatusCode());

    Movie createdMovie = createdMovieResponse.getResponseObject();

    ServerResponse<List<Subtitle>> response = getTranslatedSubtitlesByMovieId(createdMovie.getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testGetSubtitlesByMovieInvalidId() throws IOException {
    ServerResponse<List<Subtitle>> response = getTranslatedSubtitlesByMovieId((long) Integer.MAX_VALUE);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
}
