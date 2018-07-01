package com.ado.moviesub.app.entity.movie;

import com.ado.moviesub.app.entity.movie.locator.SubtitleContent;
import com.ado.moviesub.app.exception.InternalServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SubtitleParser {

  public static final String DEFAULT_VERSION = "00000000-0000-0000-0000-000000000000";

  private static final int LANGUAGE_INDEX = 0;
  private static final int MOVIE_NAME_INDEX = 1;
  private static final int VERSION_INDEX = 2;

  private static final String NAME_TOKENS_DELIMITER = "-";

  private SubtitleLineParser lineParser;

  public SubtitleParser() {
    this.lineParser = new SubtitleLineParser();
  }

  public Subtitle parse(SubtitleContent subtitleContent) {

    Subtitle.Builder subtitleBuilder = parseToSubtitleBuilder(subtitleContent.getProviderId(), subtitleContent.getSubtitleName());
    String fileContent = subtitleContent.getFileContent();

    if(fileContent != null){
      subtitleBuilder.setSubtitleLines(getSubtitleLines(fileContent));
    }

    return subtitleBuilder.build();
  }

  private Subtitle.Builder parseToSubtitleBuilder(String providerId, String fileName){
    String[] nameTokens = fileName.split(NAME_TOKENS_DELIMITER);

    String movieName = nameTokens[MOVIE_NAME_INDEX];
    String language = nameTokens[LANGUAGE_INDEX];
    String version = nameTokens.length > VERSION_INDEX ? nameTokens[VERSION_INDEX] : DEFAULT_VERSION;

    // @formatter:off
    return new Subtitle.Builder()
        .setMovie(new Movie.Builder().setName(movieName).build())
        .setLanguage(Language.fromString(language))
        .setVersion(version)
        .setProviderId(providerId);
    // @formatter:on
  }

  private List<SubtitleLine> getSubtitleLines(String content)  {
    List<SubtitleLine> subtitleLines = new ArrayList<>();

    try (BufferedReader fileContentReader = new BufferedReader(new StringReader(content))) {
      String line;
      List<String> lineChunks = new ArrayList<>();

      while ((line = fileContentReader.readLine()) != null) {
        String normalizedLine = line.trim();

        if (normalizedLine.isEmpty()) {
          subtitleLines.add(lineParser.parse(lineChunks));
          lineChunks.clear();
          continue;
        }

        lineChunks.add(normalizedLine);
      }

      return subtitleLines;
    } catch (IOException ex){
      throw new InternalServiceException("Something went wrong wile parsing subtitle");
    }
  }
}
