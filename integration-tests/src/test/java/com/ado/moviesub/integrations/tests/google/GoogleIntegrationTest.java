package com.ado.moviesub.integrations.tests.google;

import com.ado.moviesub.app.Endpoint;
import com.ado.moviesub.app.entity.movie.Subtitle;
import com.ado.moviesub.integrations.tests.BaseIntegrationTest;
import com.ado.moviesub.integrations.tests.util.ServerResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public abstract class GoogleIntegrationTest extends BaseIntegrationTest {

  protected static final String EXISTING_SUBTITLE_ID = "1556Mlh1e5tzvWE1-AdU9ITMPY1NPMpwn";

  protected ServerResponse<Subtitle> getTranslatedSubtitleById(String providerId){
    return getResource(Endpoint.TRANSLATED_SUBTITLE, providerId, Subtitle.class);
  }

  protected ServerResponse<Subtitle> getTranslatedSubtitleWithLines(String providerId){
    String endpoint = Endpoint.TRANSLATED_SUBTITLE.toEndpointPath(providerId).concat("/full");

    ResponseEntity<String> responseEntity = restClient.exchange(endpoint, HttpMethod.GET, null ,String.class);
    return ServerResponse.ofObject(responseEntity, Subtitle.class);
  }

  protected ServerResponse<List<Subtitle>> getAllSubtitles(){
    return getResources(Endpoint.TRANSLATED_SUBTITLE, Subtitle.class);
  }

  protected ServerResponse<List<Subtitle>> getTranslatedSubtitlesByMovieId(Long movieId){
    String filter = String.format("movieId=%s", movieId);
    String endpoint = Endpoint.TRANSLATED_SUBTITLE.toEndpointPathWithFilter(filter);

    ResponseEntity<String> responseEntity = restClient.exchange(endpoint, HttpMethod.GET, null ,String.class);
    return ServerResponse.ofCollection(responseEntity, Subtitle.class);
  }
}
